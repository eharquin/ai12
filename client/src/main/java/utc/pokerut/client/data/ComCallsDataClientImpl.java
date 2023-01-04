package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.ComCallsData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComCallsDataClientImpl implements ComCallsData {
    private Core myDataCore;

    public ComCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

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
        myDataCore.addNewPlayer(player);
    }

    @Override
    public void sendLists(List<Player> players, List<Game> games){

        myDataCore.setConnectedPlayers((ArrayList<Player>) players);
        myDataCore.setWaitingGame((ArrayList<Game>) games);
    }

    @Override
    public void updateGameList(Game game) {
        myDataCore.addWaitingGame(game);
        if(game.getCreator().getId() == myDataCore.getProfile().getId())
            myDataCore.getiDataCallsIHMMain().displayGame(game);

    }

    @Override
    public boolean joinTableRequestComGameCreator(String username, UUID gameID) {
        return myDataCore.getiDataCallsIHMMain().joinTableRequestComGameCreator(username, gameID);
    }

    @Override
    public void notifyRejectionComMainCli(UUID gameID, UUID playerID) {
        myDataCore.getiDataCallsIHMGame().notifyRejectionComMainCli(gameID, playerID);
    }
}
