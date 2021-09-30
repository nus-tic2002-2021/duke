
import java.util.ArrayList;

public class Parser {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static Command test;

    public static boolean parse(String cmd) throws DukeException {

        try {
            switch (cmd) {
                case "bye":
                    return parseBye();
                case "list":
                    return parseList(cmd);
                default:
                    return CmdsParser.parse(cmd);
            }
        }

        catch (DukeException e) {
            throw new DukeException();
        }

    }

    public static boolean parseBye () {
        Printer.printBye();
        return false;
    }

    public static boolean parseList(String cmd) {
        if (taskList.size() == 0) Printer.printNoTask();
        else Printer.printList();
        return true;
    }

}
