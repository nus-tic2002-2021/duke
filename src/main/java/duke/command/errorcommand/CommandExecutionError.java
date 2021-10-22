package duke.command.errorcommand;

import java.util.List;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;

public class CommandExecutionError extends Command {
    public CommandExecutionError(Exception e, String when) {
        super(ResponseType.ERROR_COMMAND_EXECUTION, List.of("Error during execution of command ", when, e.toString()));
    }
}
