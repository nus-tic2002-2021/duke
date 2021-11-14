package duke.command;

import duke.task.Task;

/**
 * Done Command for a task from taskList
 */

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    int numToComplete;

    public DoneCommand(String[] words) {
        this.numToComplete = Integer.parseInt(words[1]);
    }

    /**
     * complete the task at the location of numToComplete
     * @return CommandResult with specific message
     */
    @Override
    public CommandResult execute() {
        Task completed = taskList.setDone(numToComplete);
        return new CommandResult(getDoneSuccessful(completed));
    }

    public String getDoneSuccessful(Task completed) {
        return "Marvelous! I've marked this task as done:\n"
                + completed + "\n"
                + "Now you have " + taskList.getSize() + " tasks in the list.";
    }
}
