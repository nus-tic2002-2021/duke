package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    String[] command;

    public DoneCommand(String[] command){
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int taskNumber = Integer.parseInt(command[1]) - 1;
        taskList.get(taskNumber).setDone(true);
        ui.printTaskCount(taskList);
        storage.saveTask(taskList.getTasks());
    }

}
