package duke.command;

import java.io.*;
import duke.exception.*;
import duke.ui.*;
import duke.storage.*;
import duke.tasklist.*;

public abstract class Command {

    protected boolean isExit = false;

    public Command(){}

    public Command(String str){ System.out.println(str);}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public void setExit(boolean exit){
        isExit = exit;
    }

    public boolean isExit(){
        return isExit;
    }

}
