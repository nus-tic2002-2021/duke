package duke.parser;

import duke.DukeException;
import duke.command.*;


import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

    public Command parseCommand(String userCommandText) {
        String[] words = userCommandText.split(" ");
        String commandWord = words[0];
        String Text = words.length == 1 ? "" : Parser.getArguments(words);

        try {
            switch (commandWord) {
                case List.COMMAND_WORD:
                    return new List();
                case DoneCommand.COMMAND_WORD:
                    return new DoneCommand(words);
                case Delete.COMMAND_WORD:
                    return new Delete(words);
                case TodoCommand.COMMAND_WORD:
                    return new TodoCommand(Text);
                case DeadlinesCommand.COMMAND_WORD:
                    return new DeadlinesCommand(Text);
                case EventCommand.COMMAND_WORD:
                    return new EventCommand(Text);
                case Exit.COMMAND_WORD:
                    return new Exit();
                case Find.COMMAND_WORD:
                    return new Find(Text);
                default:
                    throw new DukeException("Sorry, I do not understand what you mean.");
            }
        } catch (DukeException | DateTimeParseException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Combines a String array from index 1 to (length - 1)
     */
    public static String getArguments(String[] parts) {
        String result = parts[1];
        for (int i = 2; i < parts.length; i++) {
            result += " " + parts[i];
        }
        return result;
    }
}
