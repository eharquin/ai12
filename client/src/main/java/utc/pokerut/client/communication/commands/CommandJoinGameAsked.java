package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.JoinGameAsked;
import utc.pokerut.common.messages.NotifyAcceptance;
import utc.pokerut.common.messages.NotifyRejection;

public class CommandJoinGameAsked extends ClientCommand<JoinGameAsked> {
    @Override
    public void execute() {
        boolean res = core.getComCallsData().joinTableRequestComGameCreator("", message.gameID);

        if(res)
            core.getClient().send(new NotifyRejection(message.gameID, message.playerID));
        else
            core.getClient().send(new NotifyAcceptance(message.gameID, message.playerID));
    }
}
