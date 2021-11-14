package duke.command;

import duke.task.Deadlines;
import duke.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Adds a deadline to the taskList
 */

public class DeadlinesCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    String taskName;
    LocalDate on;

    /**
     * creates a DeadlinesCommand
     * @param Text input by the user
     * @throws DukeException if the command is empty
     */

    public DeadlinesCommand(String Text) throws DukeException, DateTimeParseException {
        String[] deadParts = Text.split(" /at ");
        if (deadParts[0].equals("") || deadParts[1].equals("")) {
            throw new DukeException("The description of an deadline cannot be empty");
        } else {
            this.taskName = deadParts[0];
            this.on = LocalDate.parse(deadParts[1]);
        }
    }

    @Override
    public CommandResult execute() {
        Task newDeadlines = new Deadlines(taskName, false, on);
        taskList.addTasks(newDeadlines);
        return new CommandResult(addTaskSuccess(newDeadlines));
    }
}
