package duke.exception;

public class UnknownSyntaxException extends DukeException{
    /**
     * Throws when user input does not categorize under any of the pre-defined message.
     *
     * @param message   the pre-defined message.
     */
    public UnknownSyntaxException(String message) {
        super(message);
    }
}
