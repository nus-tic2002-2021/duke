package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.exceptions.InvalidCommandFormatException;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.regex.Pattern;

public class DeleteCommand extends Command {
    private final String REGEX_PATTERN = "^\\b(delete)\\b (.+)$";

    public DeleteCommand() {
        setCommandType(CommandType.REMOVE);
    }

    public DeleteCommand(String keyword, String args) {
        super(keyword, args);
        setCommandType(CommandType.REMOVE);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public DeleteCommand build() {
        return new DeleteCommand(getKeyword(), getArgs());
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) throws InvalidCommandFormatException {
        int idx = Integer.parseInt(getArgs()) - 1;
        if (idx > tasks.size() - 1) {
            throw new InvalidCommandFormatException(InvalidCommandFormatException.ERROR_NO_SUCH_INDEX);
        }
        Task removedTask = tasks.remove(idx);
        return prompt.remove(removedTask, tasks.size());
    }
}
