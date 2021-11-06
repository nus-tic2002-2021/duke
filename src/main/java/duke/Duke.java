package duke;

import duke.ui.*;
import duke.tasklist.*;
import duke.exception.*;
import duke.parser.*;
import duke.storage.*;
import duke.command.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.exceptionMessage(e);
        } catch (FileNotFoundException e) {
            ui.exceptionMessage(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        Ui.Welcome();
        boolean isExit = false;
        while(!isExit){
            try {
                String userInput = ui.userInput();
                ui.Separator();
                Command c = Parser.parse(userInput, taskList);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e){
                ui.errorMessage(e);
            } finally {
                ui.Separator();
            }
        }
    }

    public static void main(String[] args){
        new Duke("src/data/tasks.txt").run();
    }
}
