package duke.command;

/**
 * Exit from the chat
 */

public class Exit extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_MESSAGE = "Bye, hope to see you again";

    @Override
    public CommandResult execute() {
        return new CommandResult(BYE_MESSAGE);
    }

    public static boolean isExitCommand(Command c) {
        return c instanceof Exit;
    }
}


