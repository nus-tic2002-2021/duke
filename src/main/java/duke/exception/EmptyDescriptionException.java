package duke.exception;

public class EmptyDescriptionException extends DukeException{

    /**
     * Throws when user did not give a description for the task.
     *
     * @param message   the pre-defined message.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }

}
