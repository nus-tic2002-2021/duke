package duke.ui;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

import static duke.parser.Parser.parseDate;

public class Ui {

    private static final String SEPARATOR =
            "___________________________________________________________________________";

    private final Scanner in = new Scanner(System.in);

    public String userInput(){
        return in.nextLine();
    }
    public static void Welcome (){
        System.out.println("        _..._\n" +
                "      .'     '.      _\n" +
                "     /    .-\"\"-\\   _/ \\\n" +
                "   .-|   /:.   |  |   |\n" +
                "   |  \\  |:.   /.-'-./\n" +
                "   | .-'-;:__.'    =/\n" +
                "   .'=  *=|DUKE _.='\n" +
                "  /   _.  |    ;\n" +
                " ;-.-'|    \\   |\n" +
                "/   | \\    _\\  _\\\n" +
                "\\__/'._;.  ==' ==\\\n" +
                "         \\    \\   |\n" +
                "         /    /   /\n" +
                "         /-._/-._/\n" +
                "         \\   `\\  \\" + "\t\tAye there. I'm DUKE.\n" +
                "          `-._/._/" + "\t\tHow can i help you?");
        System.out.println("___^/\\___^--____/\\____O______________/\\/\\---/\\___________---______________ \n" +
                "   /\\^   ^  ^    ^                  ^^ ^  '\\ ^          ^       ---         ");
        System.out.println(SEPARATOR);
    }

    public static void goodBye (){
        System.out.println("                                                   ,:\n" +
            "                                                 ,' |\n" +
            "                                                /   :\n" +
            "                                             --'   /\n" +
            "                                             \\/ />/\n" +
            "                                             / /_\\\n" +
            "                                          __/   /\n" +
            "                                          )'-. /\n" +
            "                                          ./  :\\\n" +
            "                                           /.' '\n" +
            "                                         '/'\n" +
            "                                         +\n" +
            "                                        '\n" +
            "                                      `.\n" +
            "                                  .-\"-\n" +
            "                                 (    |\n" +
            "                              . .-'  '.\n" +
            "                             ( (.   )8:\n" +
            "                         .'    / (_  )\n" +
            "                          _. :(.   )8P  `\n" +
            "                      .  (  `-' (  `.   .\n" +
            "                       .  :  (   .a8a)\n" +
            "                      /_`( \"a `a. )\"'\n" +
            "                  (  (/  .  ' )=='\n" +
            "                 (   (    )  .8\"   +\n" +
            "                   (`'8a.( _(   (\n" +
            "                ..-. `8P    ) `  )  +\n" +
            "              -'   (      -ab:  )\n" +
            "            '    _  `    (8P\"Ya\n" +
            "          _(    (    )b  -`.  ) +\n" +
            "         ( 8)  ( _.aP\" _a   \\( \\   *\n" +
            "       +  )/    (8P   (88    )  )\n" +
            "          (a:f   \"     `\"       `\n" +
            "\nBye, i will miss you.");
    }

    public void errorMessage (Exception e) {
        System.out.println(e.getMessage());
    }

    public void exceptionMessage (Exception e) {
        System.err.println(e.getMessage());
    }

    public static void validateEventCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/at")) {
            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty. Please re-enter:");
        }
    }

    public static void validateDeadlineCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. Please re-enter:");
        } else if (!command[1].contains("/by")) {
            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty. Please re-enter:");
        }
    }

    public static void validateTodoCommand(String[] command) {
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. Please re-enter:");
        }
    }

    public void Separator() {System.out.println(SEPARATOR);}

    public void printAddCommand(TaskList taskList){
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t"+taskList.get(taskList.size()-1).printTask());
    }

    public void printTaskList(TaskList taskList) throws NullPointerException{
        if (taskList.size() == 0) {
            System.out.println("List is empty. Please add new task.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + taskList.get(i).printTask());

            }
        }
    }


    public static void validateDoneCommand(String[] command, TaskList taskList){
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ Please state task number.");
        }
        int taskNumber  = Integer.parseInt(command[1]) - 1;
        if (taskNumber >= taskList.size()) {
            throw new DukeException("☹ There is no such task.");
        }

    }

    public static void printDone(String[] command, TaskList taskList){
        int taskNumber  = Integer.parseInt(command[1]) - 1;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t"+taskList.get(taskNumber).printTask());
    }

    public static void printDelete(String[] command, TaskList taskList){
        int taskNumber  = Integer.parseInt(command[1]) - 1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println("\t"+taskList.get(taskNumber).printTask());
    }

    public void printTaskCount(TaskList taskList){
        System.out.println("Now you have total " + (taskList.size()) +" tasks in the list.");
    }

    public void printTaskListStr(TaskList taskList) {
        System.out.println("You have total " + (taskList.size()) + " tasks in the list.");
    }

    public void printTaskOfDate(TaskList taskList, LocalDate date){
        System.out.println("You have total " + (taskList.size()) +" tasks on " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))+".");
    }

    public static String validateDateTime(){
        return "Task cannot be added. \n" +
                "Please enter datetime in the format of 'dd/MM/yyyy HHmm'";
    }

    public static void validateViewCommand(String[] command){
        LocalDate dateTime = parseDate(command);
        if (command.length < 2) {
            throw new DukeException("☹ OOPS!!! The date cannot be empty. Please re-enter:");
        }
    }

    public static void validateSearchCommand(String[] command){
        if (command.length < 2 || command[1].equals("")) {
            throw new DukeException("☹ OOPS!!! Please specify keyword:");
        }
    }

    public void printTaskByKeyword(TaskList taskList, String keyword){
        System.out.println("You have total " + (taskList.size()) +" tasks related to <" + keyword +">.");
    }

}