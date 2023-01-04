package utc.pokerut.client.communication.commands;

import utc.pokerut.common.messages.GameRequested;

public class CommandJoinGameRequested extends ClientCommand<GameRequested> {
    @Override
    public void execute() {
        // to implement on data side
        //core.getComCallsData().joinTableRequestComGameCreator(player, game);
    }
}
