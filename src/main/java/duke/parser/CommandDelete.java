package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandDelete extends CommandBase {

    /**
     * Creates delete command constructor.
     *
     * @param command full command.
     */
    public CommandDelete(String command) {
        super(command);
    }

    /**
     * Executes delete command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        taskList.deleteTask(Integer.parseInt(super.fullCommand.split(" ", 2)[1]));
        return false;
    }
}
