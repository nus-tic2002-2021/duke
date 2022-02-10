package duke.parser;

public class CommandParser {

    /**
     * Generates different CMD objects according to the input parameters
     * @see CommandBase
     */
    public static Command parse(String fullCommand) {
        Command command = new Command();
        if (fullCommand.contains(CommandEnums.DONE.getName())) {
            command.setCmd(new CommandDone(fullCommand));
        } else if (fullCommand.contains(CommandEnums.LIST.getName())) {
            command.setCmd(new CommandList(fullCommand));
        } else if (fullCommand.contains(CommandEnums.DELETE.getName())) {
            command.setCmd(new CommandDelete(fullCommand));
        } else if (fullCommand.contains(CommandEnums.BYE.getName())) {
            command.setCmd(new CommandBye(fullCommand));
        } else if (fullCommand.contains(CommandEnums.SAVE.getName())) {
            command.setCmd(new CommandSave(fullCommand));
        } else if (fullCommand.contains(CommandEnums.TODO.getName())) {
            command.setCmd(new CommandTodo(fullCommand));
        } else if (fullCommand.contains(CommandEnums.EVENT.getName())) {
            command.setCmd(new CommandEvent(fullCommand));
        } else if (fullCommand.contains(CommandEnums.DEADLINE.getName())) {
            command.setCmd(new CommandDeadline(fullCommand));
        } else if(fullCommand.contains(CommandEnums.FIND.getName())) {
            command.setCmd(new CommandFind(fullCommand));
        } else if(fullCommand.contains(CommandEnums.VIEW.getName())) {
            command.setCmd(new CommandView(fullCommand));
        } else {
            command.setCmd(new CommandError(fullCommand));
        }

        return command;
    }
}
