package duke.command;

/**
 * Command to list tasks
 */

public class List extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * list all the tasks in taskList
     * @return taskList
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.getAllTasks());
    }
}
