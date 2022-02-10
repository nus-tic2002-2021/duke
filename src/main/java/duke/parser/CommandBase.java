package duke.parser;

import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyTaskListException;
import duke.exception.TaskNotFoundException;
import duke.exception.UnknownSyntaxException;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.ReturnMessages;
import duke.ui.UI;

/**
 * Creates base command class.
 * CommandBase is the base class of all command objects. It contains some public attributes and methods.
 * Each class that inherits CommandBase only needs to perform its own duty.
 */
public class CommandBase {

    /** Declares command user input. */
    protected String fullCommand;

    /** Declares command to be executed. */
    protected String keyword;

    /** Declares command parameters. */
    protected String detail;

    /** Returns message object. */
    private static ReturnMessages returnMessage = new ReturnMessages();

    /**
     * Tells the command to parse into keyword and detail(If needed).
     *
     * @param command full command of user input.
     * @throws IndexOutOfBoundsException thrown when able to identify keyword but not the rest of the descriptions
     * @throws UnknownSyntaxException thrown when syntax is unreadable
     * @throws EmptyDescriptionException thrown after detecting description is empty
     */
    public CommandBase(String command) throws ArrayIndexOutOfBoundsException, UnknownSyntaxException, EmptyDescriptionException{
        this.fullCommand = command;
        this.keyword = "";
        this.detail = "";
        if(command.contains(CommandEnums.TODO.getName()) || command.contains(CommandEnums.DEADLINE.getName()) || command.contains(CommandEnums.EVENT.getName())  || command.contains(CommandEnums.VIEW.getName()) || command.contains(CommandEnums.FIND.getName())){
            try {
                String[] toWords = command.split(" ", 2);
                this.keyword = toWords[0];
                this.detail = toWords[1];
            } catch(ArrayIndexOutOfBoundsException aio) {
                if(command.contains(CommandEnums.TODO.getName()) || command.contains(CommandEnums.DEADLINE.getName()) || command.contains(CommandEnums.EVENT.getName())){
                    throw new EmptyDescriptionException(keyword);
                } else {
                    throw new UnknownSyntaxException(command);
                }
            }
        }
    }

    public CommandBase() {

    }

    /**
     * Executes command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     */
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList){
        return false;
    }

    /**
     * Replies a done/add command.
     *
     * @param taskList gets the full task list.
     * @throws EmptyTaskListException thrown if task list is empty.
     * @throws UnknownSyntaxException thrown if syntax is unknown.
     * @throws TaskNotFoundException thrown if task is not found.
     */
    public  void reply(TaskList taskList) throws EmptyTaskListException, UnknownSyntaxException, TaskNotFoundException {
        if(fullCommand.contains("done")){
            assert fullCommand.contains("done"):"It should have confirmed a done command by this stage";
            try {
                String[] doneCommand = fullCommand.split(" ");
                if(Integer.parseInt(doneCommand[1]) - 1 >= taskList.getListSize()){
                    throw new TaskNotFoundException("you only have "+taskList.getListSize()+" and you entered "+Integer.parseInt(doneCommand[1]));
                }
                if(taskList.getTask(Integer.parseInt(doneCommand[1]) - 1).isDone()){
                    returnMessage.taskAlreadyDoneFeedback();
                } else{
                    taskList.getTask(Integer.parseInt(doneCommand[1]) - 1).setDone();
                    returnMessage.taskDoneFeedback();
                    System.out.println("     " + taskList.getTask(Integer.parseInt(doneCommand[1]) - 1).toString());
                }
                returnMessage.separator();
                return;
            } catch(NumberFormatException nf){
                throw new TaskNotFoundException("task does not exist");
            }
        }
        returnMessage.taskAddFeedback();
        assert taskList.getListSize()>0:"There should at least have 1 task";
        try {
            System.out.println("     " + taskList.getLastTask().toString());
            System.out.println("     Now you have " + taskList.getListSize() + " tasks in the list.");
            returnMessage.separator();
        } catch(ArrayIndexOutOfBoundsException a){
            throw new EmptyTaskListException("empty task");
        }
    }
}
