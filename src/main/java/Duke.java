import java.util.Scanner;
import java.io.*;

public class Duke {

    public static void msgGreet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println("_________________________________");
    }

    public static void msgBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________");
        msgBuddahProtectMe();
    }

    public static void msgBuddahProtectMe() throws IOException {
        String everything = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/buddha.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            System.out.println(everything);
        }

    }

    public static void echo(String s) {
        System.out.println("    added: " + s);
        System.out.println("_________________________________");
    }

    public static void main(String[] args) throws IOException {
        msgGreet();

        boolean bye = false;

        String line;
        Scanner in = new Scanner(System.in);

        List myList = new List();

        while (!bye) {
            line = in.nextLine();
            if (line.equals("list")) {
                for (int i = 0; i < myList.getNumOfItem(); i++) {
                    System.out.println("    " + Integer.toString(i) + ": " + myList.getItem(i));
                }
                System.out.println("_________________________________");
            } else if (line.equals("bye")) {
                bye = true;
            } else {
                myList.addItem(line);
                echo(line);
            }
        }

        in.close();
        msgBye();
    }
}
