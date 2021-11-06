package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class SearchCommand extends Command{

    String keyword;

    public SearchCommand(String keyword){ this.keyword = keyword;}

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList taskByKeyword = taskList.getTaskByKeyword(keyword, taskList);
        ui.printTaskList(taskByKeyword);
        ui.printTaskByKeyword(taskByKeyword,keyword);
    }

}
