package duke.coronet.orchestra;

import duke.coronet.command.Command;
import duke.coronet.command.commandFactory.UxCommandFactory;
import duke.coronet.command.errorCommand.CommandExecutionError;
import duke.coronet.command.taskCommand.taskQuery.CommandListAll;
import duke.coronet.manager.FileResourceManager;
import duke.coronet.manager.TaskManager;
import duke.coronet.manager.UxManager;

import static duke.coronet.dukeUtility.validator.TextCommandValidator.isRequestExitLoop;
import static duke.coronet.dukeUtility.validator.TextCommandValidator.isRequestList;

public class OrchestratorLevel02 extends AbstractOrchestrator {

    public static void main(String[] args) throws Exception {
        new OrchestratorLevel02(new TaskManager(), null, new UxManager(System.out)).run();
    }

    public OrchestratorLevel02(TaskManager tm, FileResourceManager frm, UxManager uxMgr) {
        super(tm, frm, uxMgr);
        uxMgr.setUxCommandFactory(new UxCommandFactory() {
            @Override
            public Command executeTextCommand(String text, TaskManager taskManager, FileResourceManager frm) {
                try {
                    if (isRequestExitLoop(text)) {
                        return this.executeCommandExitLoop();
                    } else if (isRequestList(text)) {
                        return new CommandListAll(taskManager);
                    } else {
                        return this.executeCommandAddToDo(text, taskManager);
                    }
                } catch (Exception e) {
                    return new CommandExecutionError(e, "command execution @ cli");
                }
            }
        });
    }

    @Override
    public void run() throws Exception {
        this.printEntryMessage();
        this.inputLoop();
        this.printExitMessage();
    }
}
