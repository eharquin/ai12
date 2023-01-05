package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.*;
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
        return myDataCore.getiDataCallsIHMGame().joinTableRequestComGameCreator(username, gameID);
    }

    @Override
    public void notifyRejectionComMainCli(UUID gameID, UUID playerID) {
        myDataCore.getiDataCallsIHMGame().notifyRejectionComMainCli(gameID, playerID);
    }

    @Override
    public void newCurrentGame(Game game) {
        Game currentGame = myDataCore.getCurrentGame();
        currentGame.setStatus(StatusEnum.ON_GOING);
        myDataCore.getiDataCallsIHMMain().displayGame(currentGame);
        myDataCore.getiDataCallsIHMMain().notifyGameReady(currentGame);
    }

    @Override
    public void displayResults(List<Result> results) {
        myDataCore.getiDataCallsIHMGame().displayResults(results);
    }

    @Override
    public void sendUserActionRefused(Action action) {

    }

    @Override
    public void addUserToGameComDataCli(Game gameNewPlayer, Player newPlayer, UUID idUser) {
        myDataCore.setCurrentGame(gameNewPlayer);
        myDataCore.getiDataCallsIHMMain().addUserToGameDataMainCli(gameNewPlayer, newPlayer, idUser);
    }

    @Override
    public void updateTableNewPlayerComDataOthers(Game gameNewPlayer, Player newPlayer, UUID idUser) {
       // myDataCore.setCurrentGame(gameNewPlayer);
        myDataCore.getCurrentGame().addPlayer(newPlayer);
        myDataCore.getiDataCallsIHMGame().newPlayerJoinedDataGameOthers(gameNewPlayer, newPlayer, idUser);
    }

    @Override
    public void notifyNextPlayerPossibleActions(List<Action> actions) {
        myDataCore.getiDataCallsIHMGame().displayPossibleActions(actions);
    }

    @Override
    public void updateRound(Round round) {
        myDataCore.getCurrentGame().updateCurrentRound(round);
    }

    @Override
    public void updateGameEnd(Round round, List<Result> results) {
        myDataCore.getCurrentGame().endGame(round);
        myDataCore.getiDataCallsIHMGame().displayResults(results);
    }

    @Override
    public void updateNewRound(Round round) {
        myDataCore.getCurrentGame().addRound(round);
    }

    public void userDisconnected(UUID playerID) {
        Player player = myDataCore.getConnectedPlayer(playerID);
        myDataCore.removePlayer(player);
    }

    @Override
    public void logoutUser(UUID idUser) {
        // TODO
    }

}
