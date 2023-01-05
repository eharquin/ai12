package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.LaunchGame;

public class CommandLaunchGame extends ClientCommand<LaunchGame> {
    @Override
    public void execute() {
        core.getComCallsData().newCurrentGame(message.game);
    }
}
