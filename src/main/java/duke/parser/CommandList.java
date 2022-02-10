package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandList extends CommandBase {
    /**
     * Creates list Command Constructor
     *
     */
    public CommandList(String command) {
        super(command);
    }

    /**
     * Executes list command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storageTaskList  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        taskList.printTaskList();
        return false;
    }
}