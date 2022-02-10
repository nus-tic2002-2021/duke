package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandSave extends CommandBase {
    /**
     * Creates save Command Constructor
     *
     */
    public CommandSave(String command){
        super(command);
    }

    /**
     * Executes save command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storageTaskList  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        storageTaskList.save(taskList);
        return false;
    }
}
