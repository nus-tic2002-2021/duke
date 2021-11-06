package duke.command;

import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.saveTask(taskList.getTasks());
        ui.printAddCommand(taskList);
        ui.printTaskCount(taskList);
    }


}
