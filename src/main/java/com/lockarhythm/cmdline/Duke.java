package com.lockarhythm.cmdline;

import java.util.Scanner;
import java.util.Arrays;

import com.lockarhythm.responders.QueryRespondable;
import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.exit.ExitResponder;
import com.lockarhythm.responders.addlist.AddListResponder;
import com.lockarhythm.responders.markasdone.MarkAsDoneResponder;
import com.lockarhythm.tasks.TaskList;

public class Duke {
    static String logo =   " \t____        _        \n"
                           + "\t|  _ \\ _   _| | _____ \n"
                           + "\t| | | | | | | |/ / _ \\\n"
                           + "\t| |_| | |_| |   <  __/\n"
                           + "\t|____/ \\__,_|_|\\_\\___|\n";

    static TaskList list = new TaskList();

    static QueryRespondable[] responders = {
        new ExitResponder(),
        new MarkAsDoneResponder(list),
        new AddListResponder(list),
    };

    private static void print(String ...strings) {
        System.out.println("\t____________________________________________________________");
        for (String s : strings) {
            s = Arrays.stream(s.split("\n")).map(x -> "\t" + x).reduce("", (x, y) -> x + y + "\n");
            System.out.println(s);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    private static Response getResponse(String query) {
        Response res = null;
        for (QueryRespondable responder : responders) {
            res = responder.respondTo(query);
            if (res != null) {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        print("Hello I'm\n" + logo, "What can I do for you?");

        line = in.nextLine();
        Response res = getResponse(line);
        while(!res.shouldExit()) {
            print(res.getText());
            line = in.nextLine();
            res = getResponse(line);
        }

        print(res.getText());
    }
}
