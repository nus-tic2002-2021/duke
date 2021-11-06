package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    String[] command;

    public DeleteCommand(String[] command){
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int taskNumber = Integer.parseInt(command[1]) - 1;
        taskList.removeTask(taskNumber);
        ui.printTaskCount(taskList);
        storage.saveTask(taskList.getTasks());
    }
}
