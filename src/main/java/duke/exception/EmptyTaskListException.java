package duke.exception;


public class EmptyTaskListException extends DukeException{

    /**
     * Throws when the task list is empty.
     *
     * @param message Printing message overload.
     */
    public EmptyTaskListException(String message) {
        super(message);
    }
    /*
    */
}
