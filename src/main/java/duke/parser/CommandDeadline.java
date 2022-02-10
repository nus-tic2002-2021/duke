package duke.parser;

import duke.exception.TimeManagementException;
import duke.exception.TimeParseException;
import duke.exception.UnknownSyntaxException;
import duke.parser.timeHelper.DateTimeParse;
import duke.storage.StorageTaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.LocalDateTime;


public class
CommandDeadline extends CommandBase {

    private DateTimeParse timeHelp = new DateTimeParse();

    /**
     * Creates deadline constructor.
     *
     * @param command full command.
     */
    public CommandDeadline(String command){
        super(command);
    }


    /**
     * Executes deadline command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     * @throws TimeManagementException thrown when datetime not specified
     * @throws TimeParseException thrown when datetime format is wrong
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws TimeManagementException, TimeParseException{
        boolean isSuccess = false;
        try {
            if (!CommandEnums.DEADLINE.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            String[] deadlineCommand = super.detail.split("/by", 2);
            //region time parse
            String[] dateTimes = deadlineCommand[1].split(" ", 2);
            LocalDateTime dateT = timeHelp.dateAndTimeParse(dateTimes[1]);
            //endregion
            Task task = new Deadline(deadlineCommand[0], dateT);
            if(taskList.addTask(task)){
                isSuccess = true;
                assert taskList.getListSize()>0:"There should at least have 1 task";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TimeManagementException();
        } catch (TimeParseException e) {
            throw new TimeParseException(e.getMessage());
        }

        if (isSuccess) {
            reply(taskList);
        }

        return false;
    }

}
