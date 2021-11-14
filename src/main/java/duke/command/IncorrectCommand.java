package duke.command;

/**
 * Pop put incorrect message
 */

public class IncorrectCommand extends Command{
    String errorMessage;

    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(errorMessage);
    }
}
