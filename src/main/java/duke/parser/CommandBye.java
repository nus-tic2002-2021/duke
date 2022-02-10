package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandBye extends CommandBase {
    /**
     * Creates bye command constructor.
     *
     * @param command Full command.
     */
    public CommandBye(String command){
        super(command);
    }

    /**
     * Executes of bye command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        storageTaskList.save(taskList);
        return true;
    }
}
