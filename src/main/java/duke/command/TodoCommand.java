package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.Todo;


public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    String taskName;

    /**
     * Creates a TodoCommand
     * @param Text input by the user
     * @throws DukeException if the Text is empty
     */
    public TodoCommand(String Text) throws DukeException {
        if (Text.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            this.taskName = Text;
        }
    }

    /**
     * Creates a to-do and adds it to the taskList
     * @return CommandResult with the success message
     */
    @Override
    public CommandResult execute() {
        Task newTodo = new Todo(taskName, false);
        taskList.addTasks(newTodo);
        return new CommandResult(addTaskSuccess(newTodo));
    }

}