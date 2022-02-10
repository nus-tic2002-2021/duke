package duke.parser;

import duke.exception.UnknownSyntaxException;
import duke.storage.StorageTaskList;
import duke.storage.StorageFixedDurationReference;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;
import duke.task.FixedDurationTaskList;
import duke.ui.UI;

public class CommandTodo extends CommandBase {
    private static StorageFixedDurationReference loadFixedDurationRecord;
    private static FixedDurationTaskList fixedDurationTaskList;
    /**
     * Creates TO DO Command
     *
     */
    public CommandTodo(String command) {
        super(command);
    }

    /**
     * Executes TO DO command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storageTaskList  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) {
        boolean isSuccess = false;
        try {
            if (!CommandEnums.TODO.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            loadFixedDurationRecord = new StorageFixedDurationReference();
            fixedDurationTaskList = new FixedDurationTaskList(loadFixedDurationRecord.load());
            int duration = fixedDurationTaskList.findRecord(super.detail);
            Task task;
            if (duration > 0) {
                task = new ToDos(super.detail, duration);
            } else{
                task = new ToDos(super.detail);
            }
            if(taskList.addTask(task)){
                isSuccess = true;
                assert taskList.getListSize()>0:"There should at least have 1 task";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownSyntaxException(super.keyword);
        }

        if (isSuccess) {
            reply(taskList);
        }

        return false;
    }
}
