package duke.parser;

import duke.exception.TimeManagementException;
import duke.exception.TimeParseException;
import duke.exception.UnknownSyntaxException;
import duke.parser.timeHelper.DateTimeParse;
import duke.storage.StorageTaskList;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.LocalDate;

public class CommandEvent extends CommandBase {

    private DateTimeParse timeHelp = new DateTimeParse();

    /**
     * Creates event Command Constructor
     *
     */
    public CommandEvent(String command)  {
        super(command);
    }


    /**
     * Executes event command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     * @throws TimeManagementException time management exception if no datetime is given
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws TimeManagementException{
        boolean isSuccess = false;
        try {
            if (!CommandEnums.EVENT.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            String[] dt = super.detail.split("/at", 2);
            String description = dt[0];
            String dateTime = dt[1].substring(1);
            LocalDate date = timeHelp.dateParse(dateTime);
            String[] dateTimeInformation = dateTime.split(" ", 2);
            String timeRange = dateTimeInformation[1];
            String from = timeRange.split("-", 2)[0];
            String to = timeRange.split("-", 2)[1];
            Task task = new Events(description, date, timeHelp.timeParse(timeHelp.get24HrFormat(from)),timeHelp.timeParse(timeHelp.get24HrFormat(to)));
            if(taskList.addTask(task)){
                isSuccess = true;
                assert taskList.getListSize()>0:"There should at least have 1 task";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TimeManagementException();
        }

        if (isSuccess) {
            reply(taskList);
        }

        return false;
    }





}