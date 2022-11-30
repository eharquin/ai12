package client.src.main.java.utc.pokerut.client.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.ComCallsData;

import java.util.List;
import java.util.UUID;

public class ComCallsDataClientImpl implements ComCallsData {
    private DataClientCore dataClientCore;
    @Override
    public void connexionUser(ServerProfile profile, String IP, int port){

    }

    @Override
    public void logoutUser(UUID playerID){

    }

    @Override
    public void modifyUser(ServerProfile profile){

    }

    @Override
    public void addUserAtList(Player player){

    }

    @Override
    public void sendLists(List<Player> players, List<Game> games){

    }

    @Override
    public void updateGameList(Game game) {
        dataClientCore.addWaitingGame(game);
    }
}
