package duke.command;

import duke.storage.Storage;
import duke.tasklist.*;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand (){}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
        ui.printTaskListStr(taskList);
    }
}
