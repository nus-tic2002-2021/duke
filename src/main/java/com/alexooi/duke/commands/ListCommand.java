package com.alexooi.duke.commands;

import com.alexooi.duke.enums.CommandType;
import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.tasks.TaskList;
import com.alexooi.duke.interfaces.OutputFormat;

import java.util.regex.Pattern;

public class ListCommand extends Command {
    private final String REGEX_PATTERN = "^(list)$";

    public ListCommand() {
        setCommandType(CommandType.LIST);
    }

    public ListCommand(String keyword) {
        super(keyword);
        setCommandType(CommandType.LIST);
    }

    @Override
    public Pattern getRegexPattern() {
        return Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public ListCommand build() {
        return new ListCommand(getKeyword());
    }

    @Override
    public String execute(TaskList tasks, OutputFormat<Task> prompt) {
        return prompt.list(tasks.get());
    }
}
