package utc.pokerut.client.ihmgame.implementations;

import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.interfaces.client.DataCallsIHMGame;

import java.util.List;
import java.util.UUID;

public class DataCallsIHMGameImp implements DataCallsIHMGame {

    private Core core;

    public DataCallsIHMGameImp(Core core){ this.core = core; }

    @Override
    public void notifyNewRoundUpdate(){}

    @Override
    public void newGameWindow(List<Action> actions){}

    @Override
    public void notifyRoundUpdate(){}

    @Override
    public void notifyRoundEndUpdate(){}

    @Override
    public void notifyPlayersInGame(UUID player_uuid, UUID game_uuid){}

    @Override
    public boolean joinTableRequestComGameCreator(String username, UUID gameID) {
        // hardcoded true
        return true;
    }

    @Override
    public boolean notifyRejectionComMainCli(UUID userUuid, UUID gameUuid) {
        return false;
    }

    @Override
    public void displayResults(List<Result> results) {

    }

}
