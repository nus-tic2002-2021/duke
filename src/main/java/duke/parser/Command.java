package duke.parser;

import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

public class Command {

    /** Declares CommandBase Object. */
    private CommandBase commandBase;

    /** Checks if the input is "bye". */
    private boolean shouldExit;

    /**
     * Checks if to exit or continue the runtime after each execution.
     *
     * @param taskList task list object
     * @param ui ui object
     * @param storageTaskList storage task list object
     */
    public void execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        this.shouldExit = commandBase.execute(taskList, ui, storageTaskList);
    }

    /**
     * Checks if isExit true.
     *
     * @return isExit true or false.
     */
    public boolean isExit() {

        return this.shouldExit;
    }

    /**
     * Returns CommandBase object.
     *
     * @return CommandBase object.
     */
    public CommandBase getCommandBase() {
        return commandBase;
    }

    /** Sets  CommandBase object. */
    public void setCmd(CommandBase commandBase) {
        this.commandBase = commandBase;
    }
}