package duke.storage;

import duke.exception.DukeException;
import duke.task.FixedDurationTask;
import duke.task.FixedDurationTaskList;
import duke.ui.ReturnMessages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StorageFixedDurationReference {

    protected boolean isExist;
    protected String fileName;
    protected File file;
    private static ReturnMessages returnMessage = new ReturnMessages();


    /** Creates Storage_Unscheduled object. */
    public StorageFixedDurationReference() {
        this.file = new File(System.getProperty("user.dir") + "/fixedDurationTask.txt"); // create a File for the given file path
        this.fileName = file.getName();
    }

    /**
     * loads info from hard disk.
     *
     * @return task list from hard disk.
     */
    public FixedDurationTaskList load() throws DukeException {
        FixedDurationTaskList unscheduledRecords = new FixedDurationTaskList();
        assert file.exists():"The file should exist by now";
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line
                    unscheduledRecords.addTask(decodeStrToFixedDurationTask(line));
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
        return unscheduledRecords;
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
     * Decodes the str to unscheduled task records with description and duration.
     *
     * @param str the full string per line.
     * @return created task object.
     */
    public FixedDurationTask decodeStrToFixedDurationTask(String str) {
        String[] line = str.split(" \\| ");
        FixedDurationTask unscheduledRecord;
        unscheduledRecord = new FixedDurationTask(line[0], Integer.parseInt(line[1]));
        return unscheduledRecord;
    }
}
