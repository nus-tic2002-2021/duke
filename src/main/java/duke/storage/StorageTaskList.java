package duke.storage;

import duke.exception.DukeException;
import duke.task.FixedDurationTaskList;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.ToDos;
import duke.task.Events;
import duke.task.Deadline;
import duke.ui.ReturnMessages;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class StorageTaskList {
    protected boolean isExist;
    protected String fileName;
    protected File file;
    private static ReturnMessages returnMessage = new ReturnMessages();
    private static StorageFixedDurationReference loadFixedDurationRecord;
    private static FixedDurationTaskList fixedDurationTaskList;

    /**
     * Checks current file existence status.
     *
     * @return true or false.
     */
    public boolean existence(){
        return isExist;
    }

    /**
     * Creates Storage object.
     *
     * @param fileName   the file saving the information of task list
     *
     */
    public StorageTaskList(String fileName) {
        this.fileName = fileName;
        this.file = new File(System.getProperty("user.dir") + "/"+fileName); // create a File for the given file path
    }

    /**
     * Loads info from hard disk.
     *
     * @return task list from hard disk.
     */
    public TaskList load() throws DukeException {
        TaskList taskList = new TaskList();
        assert file.exists():"The file should exist by now";
        if (file.exists()) {
            returnMessage.loadingFileFeedback();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line
                    taskList.addTask(decodeStrToTask(line));
                }
            } catch (IOException e) {
                //fail to parse
                throw new DukeException("file parse fail");
            }
        } else {
            try {
                createFile();
            } catch (IOException e) {
                //fail to create file
                throw new DukeException("create file fail");
            }
        }
        returnMessage.loadSuccessFeedback();
        return taskList;
    }

    /**
     * Creates File if not exist.
     *
     * @exception  IOException throws a IO Exception if file exists.
     *
     */
    public void createFile() throws IOException {
        try {
            boolean create = file.createNewFile();
        } catch (IOException ioe) {
            this.isExist = true;
            System.out.println("File exists");
        }
        this.isExist = true;
        returnMessage.createSuccessFeedback(file.getName());
    }

    /**
     * Saves task list to hard disk.
     *
     * @param taskList - data to save.
     */
    public void save(TaskList taskList)  {
        assert this.isExist:true;
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(file.getAbsoluteFile(), false);
            FileWriter finalMyWriter = myWriter;
            taskList.getTaskList().forEach((task) -> {
                try {
                    finalMyWriter.write(task.encodeTask() + "\n");
                } catch (IOException e) {
                    //
                    throw new DukeException(e.getMessage());
                }
            });
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(e.getMessage());
        }
        returnMessage.saveSuccessFeedback();
    }

    /**
     * Decodes the str to its individual task format.
     *
     * @param str the full string per line.
     * @return created task object.
     */
    public Task decodeStrToTask(String str) {
        String[] lines = str.split(" \\| ");
        Task task = null;
        switch (lines[0]) {
        case "T":
            loadFixedDurationRecord = new StorageFixedDurationReference();
            fixedDurationTaskList = new FixedDurationTaskList(loadFixedDurationRecord.load());
            int duration = fixedDurationTaskList.findRecord(lines[2]);
            if (duration > 0) {
                task = new ToDos(lines[2], duration);
            } else{
                task = new ToDos(lines[2]);
            }
            break;
        case "D":
            LocalDate date = LocalDate.parse(lines[3]);
            LocalTime time = LocalTime.parse(lines[4]);
            task = new Deadline(lines[2], LocalDateTime.of(date,time));
            break;
        case "E":
            task = new Events(lines[2], LocalDate.parse(lines[3]), LocalTime.parse(lines[4]), LocalTime.parse(lines[5]));
            break;
        }
        if (Task.DONE.equals(lines[1])) {
            if (task != null) {
                task.setDone();
            }
        }
        return task;
    }
}
