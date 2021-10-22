package duke.level;


import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.mock.mockTask.MockTask;
import duke.mock.mockTask.MockToDo;
import duke.testHelper.TestStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static duke.testHelper.help.OutputUnderTest.*;
import static duke.testHelper.help.PrettifyUnderTest.getPrettifyUnderTestList;
import static duke.testHelper.help.TextCommandUnderTest.*;
import static duke.testHelper.help.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksImportTestPathString;
import static duke.testHelper.help.config.dukeIOTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Increment_09_Test extends TestStream {
    @Test
    public void Greet_AddToDo_ListTasksWithKeyword_Save_Exit() throws Exception {




        /*
         * Should display:
         * Entry Message
         * Input Loop Message
         * "Added todo" message
         * tabled tasks list
         * exit loop
         * terminate
         */

        String keyword = "MAGIK";
        String taskDesc0 = "nons afasf09qhy2gr";
        String taskDesc1 = "aasfg " + keyword + " c124124";
        String store0Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc0);
        String store1Command= generateTextCommandLineAddToDo(PROMPT_UNDER_TEST_ADD_TO_DO, taskDesc1);
        String findCommand = generateTextCommandFindKeywordInDescription(PROMPT_UNDER_TEST_FIND,keyword);
        String exitCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);

        StringBuilder commandBuilder = new StringBuilder();

        commandBuilder.append(store0Command);
        commandBuilder.append(store1Command);
        commandBuilder.append(findCommand);
        commandBuilder.append(exitCommand);
        System.setIn(new ByteArrayInputStream(commandBuilder.toString().getBytes()));

        StringBuilder expectedResponseBuilder = new StringBuilder();

        MockToDo expectedToDo1 = new MockToDo(taskDesc1, 1, false);

        MockTask[] MockTasks = {expectedToDo1}; // only task 1 should be displayed after query
        TaskManager tm = new TaskManager();
        FileResourceManager frm = new FileResourceManager(getDefaultTasksTestExportPathString(),getDefaultTasksImportTestPathString());

        expectedResponseBuilder.append(getMsgUnderTestEntry());
        expectedResponseBuilder.append(getMsgUnderTestAttemptImport(frm.getImportPath()));
        expectedResponseBuilder.append(getMsgUnderTestReadPathNotFound());
        expectedResponseBuilder.append(getMsgUnderTestBeginInputLoop());

        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc0));
        expectedResponseBuilder.append(getMsgUnderTestResponseToDoAdded(taskDesc1));

        expectedResponseBuilder.append(getMsgUnderTestResponseList(getPrettifyUnderTestList(MockTasks)));
        expectedResponseBuilder.append(getMsgUnderTestExitLoop());
        expectedResponseBuilder.append(getMsgUnderTestTerminate());
        String expectedOutputResponse = expectedResponseBuilder.toString();


        try {
            Main.run(this.getPrintStream(), tm,frm);
        } catch (Exception e) {
            fail(e.toString());
        }

        assertEquals(expectedOutputResponse,this.getOutput());


    }


}
