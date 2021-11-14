package duke.command;

import duke.DukeException;
import duke.task.TaskList;
/**
 * Find the task according to keyword from the taskList
 */

public class Find extends Command {
    public static final String COMMAND_WORD = "find";
    String keyword;

    /**
     * Creates a Find Command
     * @param Text input by the user
     * @throws DukeException if the Text is empty
     */
    public Find(String Text) throws DukeException {
        if (Text.equals("")) {
            throw new DukeException("The find command need to follow the specific standard.");
        } else {
            this.keyword = Text;
        }
    }

    /**
     * Find tasks that matches the user input keyword
     * @return CommandResult with the list of matching tasks
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(TaskList.findTask(keyword));
    }
}
