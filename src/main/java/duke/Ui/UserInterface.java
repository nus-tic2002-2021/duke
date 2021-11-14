package duke.Ui;

import duke.command.CommandResult;

import java.util.Scanner;

public class UserInterface {

    Scanner in;
    public void UserInterface() {
        this.in = new Scanner(System.in);
    }

    public String getInput() {
        return in.nextLine();
    }

    public void showLine() {
        System.out.println("__________________");
    }
    public String getResponse(CommandResult result) {
        return result.getRespondToUser();
    }

    public void goodBye() {
        System.out.println("Bye");
    }


    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    public void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }


    public void showError(String message) {
        System.out.println(message);
    }
}
