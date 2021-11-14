package duke;

import duke.task.*;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the TaskList
 */

public class Storage {
    Path filePath;
    Path folderPath;

    private Storage() {
        String home = System.getProperty("user.home");
        filePath = Paths.get(home, "dukeData", "duke.txt");
        folderPath = Paths.get(home, "dukeData");

    }
    public void fileLocation() throws IOException {
        if (Files.notExists(folderPath)) {
            Files.createDirectory(folderPath);
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
    /**
     * Read the task list
     * @return task list
     */

    public TaskList read() {
        TaskList taskList = null;
        try {
            fileLocation();
            List<String> tasks = Files.readAllLines(filePath, Charset.defaultCharset());
            taskList = this.TaskListDecoder(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public List<String> TaskListEncoder (TaskList taskList) {
        List<String> TaskEncoder = new ArrayList<>();
        for (Task t : taskList.getList()) {
            TaskEncoder.add(t.taskEncode());
        }
        return TaskEncoder;
    }

    public TaskList TaskListDecoder(List<String> TaskEncoder) {
        List<Task> TaskDecoder = new ArrayList<>();
        for (String s : TaskEncoder) {
            switch(s.charAt(0)) {
                case ('T'):
                    TaskDecoder.add(Todo.decode(s));
                    break;
                case ('D'):
                    TaskDecoder.add(Deadlines.decode(s));
                    break;
                case ('E'):
                    TaskDecoder.add(Event.decode(s));
                    break;
                default:
                    break;
            }
        }
        return new TaskList(TaskDecoder);
    }

}


