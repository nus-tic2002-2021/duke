package duke;

import duke.exception.DukeException;
import duke.parser.Command;
import duke.parser.CommandParser;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;


public class Duke {
    private static StorageTaskList storageTaskList;
    private UI ui;
    private TaskList taskList;

    /**
     * Initializes entire duke.
     *
     * @param filePath - filepath to storage.
     */
    public Duke(String filePath) {
        ui = new UI();
        ui.showWelcome();
        storageTaskList = new StorageTaskList(filePath);
        try {
            taskList = new TaskList(storageTaskList.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() throws DukeException {
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskList, ui, storageTaskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        ui.showExit();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("duke.txt").run();
    }
}
