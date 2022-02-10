package duke.exception;


public class TimeParseException extends DukeException{
    /**
     * Throws when error occurred while parsing time.
     *
     * @param message the pre-defined message.
     */
    public TimeParseException(String message) {
        super(message);
    }
}
