package duke.parser;

import duke.exception.UnknownSyntaxException;

public class CommandError extends CommandBase {

    /**
     * Creates error command constructor.
     * Used when unknown syntax is detected.
     *
     * @param command full command
     * @throws UnknownSyntaxException throws unknown syntax if unable to identify
     */
    public CommandError(String command) throws UnknownSyntaxException{
        throw new UnknownSyntaxException(command);
    }
}
