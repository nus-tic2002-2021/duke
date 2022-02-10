package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandFind extends CommandBase {
    /**
     * Creates find Command Constructor.
     *
     * @param command the full command.
     */
    public CommandFind(String command) {
        super(command);
    }

    /**
     * Executes done command.
     *
     * @param taskList the full task list for saving purpose.
     * @param ui       UI object.
     * @param storageTaskList  storage object.
     * @return returns false as this command does not end the runtime
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        try{
            taskList.printFoundTaskList(super.detail);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
