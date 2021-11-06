package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command{
    private final String errorMsg;

    public InvalidCommand(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
