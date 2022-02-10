package duke.ui;




import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyTaskListException;
import duke.exception.SavingException;
import duke.exception.TaskNotFoundException;
import duke.exception.TimeManagementException;
import duke.exception.UnknownSyntaxException;
import duke.exception.TimeParseException;

import java.util.Scanner;

public class UI{

    private static ReturnMessages returnMessage = new ReturnMessages();

    private Scanner in;

    public void showWelcome(){
        returnMessage.welcomeFeedback();
    }

    public void showLoadingError(){
        returnMessage.exception_feedback_loadingError();
    }

    /**
     * Reads cmd user input.
     *
     * @return - command read from user Input.
     */
    public String readCommand() {
        boolean end = false;
        String command;
        in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public void showLine() {
        returnMessage.separator();
    }


    /**
     * Returns the error msg will output by this.
     * All exceptions inherit DukeException.
     *
     * @param e the exception object.
     */
    public void showError(DukeException e) {
        if (e instanceof EmptyDescriptionException) {
            returnMessage.exception_feedback_emptyDescription(e.getMessage());
        } else if (e instanceof EmptyTaskListException) {
            returnMessage.exception_feedback_emptyTaskList();
        } else if (e instanceof SavingException) {
            returnMessage.exception_feedback_cantSave();
        } else if (e instanceof TaskNotFoundException) {
            returnMessage.exception_feedback_taskNotFound(e.getMessage());
        } else if (e instanceof TimeManagementException) {
            returnMessage.exception_feedback_noTimeConcept();
        } else if (e instanceof UnknownSyntaxException){
            returnMessage.exception_feedback_unknownSyntax(e.getMessage());
        } else if (e instanceof TimeParseException){
            returnMessage.exception_feedback_timeParse(e.getMessage());
        } else {
            returnMessage.exception_feedback_unknownError();
        }
    }

    public void showExit() {
        returnMessage.exitFeedback();
    }
}
