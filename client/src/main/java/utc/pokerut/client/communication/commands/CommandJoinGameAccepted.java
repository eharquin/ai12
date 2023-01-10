package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.Command;
import utc.pokerut.common.messages.JoinGameAccepted;

public class CommandJoinGameAccepted extends ClientCommand<JoinGameAccepted> {
    @Override
    public void execute() {
        core.getComCallsData().addUserToGameComDataCli(message.game, message.player, message.playerID);
    }
}
