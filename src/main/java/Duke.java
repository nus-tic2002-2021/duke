import duke.command.Command;
import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui.UserInterface;

import javax.swing.text.html.parser.Parser;

public class Duke {
    UserInterface ui;
    TaskList taskList;
    Storage storage;

    public Duke() {
        this.ui = new UserInterface();
        this.taskList = new TaskList();
    }

    public void run() {
        ui.logo();
        ui.showWelcomeMessage();
    }

    public void userCommand() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

}
