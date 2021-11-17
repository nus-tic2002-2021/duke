package com.alexooi.duke.exceptions;

public class InvalidCommandFormatException extends Exception {
    private static final String ERROR_PREFIX = "Invalid Command Format:";
    public static final String ERROR_DEADLINE =
            "Deadline should be in the format: deadline <description> /by <date> where <date> is in the format of yyyy-mm-dd.";
    public static final String ERROR_EVENT =
            "Event should be in the format: event <description> /at <date> where <date> is in the format of yyyy-mm-dd.";
    public static final String ERROR_TODO =
            "Todo should be in the format: todo <description>.";
    public static final String ERROR_VIEW_SCHEDULE =
            "View schedule should be in the format: view <date>.";
    public static final String ERROR_NO_SUCH_INDEX =
            "The task number is not valid. Please use the corresponding task number when listing the tasks";

    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorHeader() {
        return ERROR_PREFIX;
    }

    public String toString() {
        return getMessage();
    }
}
