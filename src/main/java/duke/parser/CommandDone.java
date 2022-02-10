package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class CommandDone extends CommandBase {
    /**
     * Creates done command constructor.
     *
     * @param command full command
     */
    public CommandDone(String command) {
        super(command);
    }

    /**
     * Executes done command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        reply(taskList);
        return false;
    }
}