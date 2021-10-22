package duke.inputoutputtest;

import static duke.testhelper.help.Builder.buildCommandInputStream;
import static duke.testhelper.help.Builder.buildExpectedResponse;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputBeginInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputCommandUnknown;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputEntry;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputExitInputLoop;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputImportAttempt;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputReadPathNotFound;
import static duke.testhelper.help.codeundertest.OutputUnderTest.getExpectedOutputTerminate;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.PROMPT_UNDER_TEST_EXIT_LOOP;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandExit;
import static duke.testhelper.help.codeundertest.TextCommandUnderTest.generateTextCommandRandom;
import static duke.testhelper.help.config.dukeIoTestPath.getDefaultTasksImportTestPathString;
import static duke.testhelper.help.config.dukeIoTestPath.getDefaultTasksTestExportPathString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.FileResourceManager;
import duke.Main;
import duke.TaskManager;
import duke.testhelper.TestStream;

public class TestIoNegative extends TestStream {

    @Test
    public void testUnknownCommand() throws Exception {

        String randomTextCommand = generateTextCommandRandom("s0meUnknownPrompt");
        String exitLoopCommand = generateTextCommandExit(PROMPT_UNDER_TEST_EXIT_LOOP);
        System.setIn(buildCommandInputStream(randomTextCommand, exitLoopCommand));

        TaskManager tm = new TaskManager();
        FileResourceManager frm =
            new FileResourceManager(getDefaultTasksTestExportPathString(), getDefaultTasksImportTestPathString());
        String out0 = getExpectedOutputEntry();
        String out1 = getExpectedOutputImportAttempt(frm.getImportPath());
        String out2 = getExpectedOutputReadPathNotFound();
        String out3 = getExpectedOutputBeginInputLoop();
        String out4 = getExpectedOutputCommandUnknown();
        String out5 = getExpectedOutputExitInputLoop();
        String out6 = getExpectedOutputTerminate();

        String expectedOutputResponse = buildExpectedResponse(out0, out1, out2, out3, out4, out5, out6);

        Main.run(this.getPrintStream(), tm, frm);

        assertEquals(expectedOutputResponse, this.getOutput());
    }

}
