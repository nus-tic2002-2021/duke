package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class ViewCommand extends Command {
    LocalDate date;

    public ViewCommand(LocalDate date){
        this.date = date;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskByDate = taskList.getTaskByDate(date, taskList);
        ui.printTaskList(taskByDate);
        ui.printTaskOfDate(taskByDate, date);
    }
}
