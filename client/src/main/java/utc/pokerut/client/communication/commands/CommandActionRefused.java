package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.ActionRefused;

public class CommandActionRefused extends ClientCommand<ActionRefused> {

    public void execute() {
        core.getComCallsData().sendUserActionRefused(message.action);
    }
}
