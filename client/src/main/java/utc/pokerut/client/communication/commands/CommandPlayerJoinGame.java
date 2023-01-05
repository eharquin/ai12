package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.PlayerJoinGame;

public class CommandPlayerJoinGame extends ClientCommand<PlayerJoinGame> {
    @Override
    public void execute() {
        core.getComCallsData().updateTableNewPlayerComDataOthers(message.game, message.profile, message.playerID);
    }
}
