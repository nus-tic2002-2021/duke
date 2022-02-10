package duke.exception;

public class TaskNotFoundException extends DukeException{
    /**
     * Throws when failed to identify task, either given wrong information or task does not exist.
     *
     * @param message   the pre-defined message.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}

