package duke.parser;

import duke.exception.EmptyTaskListException;
import duke.exception.UnknownSyntaxException;
import duke.parser.timeHelper.DateTimeParse;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;


import java.time.LocalDate;

public class CommandView extends CommandBase{

    private DateTimeParse timeHelp = new DateTimeParse();

    /**
     * Creates view schedule command constructor.
     *
     * @param command the full command.
     */
    public CommandView(String command){super(command);}


    /**
     * Executes view schedule command
     *
     * @param taskList the full task list for sorting purpose.
     * @param ui show interactive information.
     * @param storageTaskList storage object.
     * @return returns false as this command does not end the runtime
     * @throws EmptyTaskListException when task list is empty no schedule to sort
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws EmptyTaskListException{
        try{
            if (!CommandEnums.VIEW.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            LocalDate scheduleDate = timeHelp.dateParse(super.detail);
            taskList.printAsSchedule(scheduleDate);
        } catch(EmptyTaskListException e){
            throw new EmptyTaskListException("Empty");
        }
        return false;
    }

}
