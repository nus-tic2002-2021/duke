package duke.exception;

public class SavingException extends DukeException{
    /**
     * Throws when there is problem saving the current task list.
     *
     * @param message   the pre-defined message.
     *
     */
    public SavingException(String message) {
        super(message);
    }

}
