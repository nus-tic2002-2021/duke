package duke.parser;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import duke.tasklist.*;
import duke.ui.Ui;
import duke.command.*;

/**
 * A <code>Parser</code> object deals with making sense of the user command.
 */
public class Parser {
    /**
     * <code>commandToArray</code> method splits user input to an array of Strings.
     * @param text is the user input
     * @return an array of Strings
     */
    public static String[] commandToArray(String text){
        return text.split(" ",2);
    }

    public static LocalDate parseDate(String[] command) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;
        date = LocalDate.parse(command[1], formatter);
        return date;
    }

    public static LocalDateTime parseDateTime(String[] command) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse(command[1].split(" /by | /at ")[1], formatter);
        return dateTime;
    }

    public static LocalDateTime parseDateTimeFromFile(String[] command) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        LocalDateTime dateTimeFromFile;
        dateTimeFromFile = LocalDateTime.parse(command[1].split(" /by | /at ")[1], formatter);
        return dateTimeFromFile;
    }

    /**
     * <code>parse</code> method parses user input to corresponding functions of the program
     * @param text is the user input
     * @param taskList is the list of tasks
     * @return a command object for corresponding functions of the program
     * @throws IllegalArgumentException when user command is not included in the CommandCollection Enum Class
     */
    public static Command parse(String text, TaskList taskList) throws IllegalArgumentException{
        assert text != null : "Command be null";

        String[] command = commandToArray(text);
        String instruction = command[0].toUpperCase();
        LocalDateTime dateTime;

        CommandCollections commandCollections;
        try {
            commandCollections = CommandCollections.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("☹ The command <"+ command[0] +"> is not valid! Please re-enter:");
        }


        switch (commandCollections) {
            case EVENT:
                try{
                    Ui.validateEventCommand(command);
                    dateTime = parseDateTime(command);
                    return new AddCommand(new Event(text,dateTime));
                }catch (DateTimeParseException e) {
                    return new InvalidCommand(Ui.validateDateTime());
                }
            case TODO:
                Ui.validateTodoCommand(command);
                return new AddCommand(new Todo(text));
            case DEADLINE:
                try{
                    Ui.validateDeadlineCommand(command);
                    dateTime = parseDateTime(command);
                    return new AddCommand(new Deadline(text,dateTime));
                }catch (DateTimeParseException e) {
                    return new InvalidCommand(Ui.validateDateTime());
                }
            case LIST:
                return new ListCommand();
            case DONE:
                Ui.validateDoneCommand(command, taskList);
                Ui.printDone(command, taskList);
                return new DoneCommand(command);
            case DELETE:
                Ui.validateDoneCommand(command, taskList);
                Command delete = new DeleteCommand(command);
                Ui.printDelete(command, taskList);
                return delete;
            case VIEW:
                try{
                    Ui.validateViewCommand(command);
                    LocalDate date = parseDate(command);
                    return new ViewCommand(date);
                }catch (DateTimeParseException e) {
                    return new InvalidCommand("☹ Please enter datetime in the format of 'd/M/yyyy'");
                }
            case SEARCH:
                Ui.validateSearchCommand(command);
                String keyword = command[1];
                return new SearchCommand(keyword);
            case BYE:
                return new ByeCommand();
            default:
                return new InvalidCommand("☹ Sorry, I don't know what it means :-(");
        }

    }
}
