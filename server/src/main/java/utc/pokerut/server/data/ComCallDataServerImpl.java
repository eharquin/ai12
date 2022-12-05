package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.ComCallsData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComCallDataServerImpl implements ComCallsData {
    private Core dataServerCore;

    public ComCallDataServerImpl(Core dataServerCore) {
        this.dataServerCore = dataServerCore;
    }

    @Override
    public void sendUpdateRound(Round round, List<UUID> Players) {

    }

    @Override
    public void sendUpdateNextRound(Round round, List<UUID> players) {

    }

    @Override
    public void sendNextPlayerActions(List<Action> actions, UUID nextPlayer) {

    }

    @Override
    public void sendUserActionRefused() {

    }

    @Override
    public void sendUpdateRoundAndNewRound(Round round, Round newRound, List<UUID> players) {

    }

    @Override
    public void transmitLeaveMessage(UUID idPlayer, UUID idGame, int nbCreditFinal, boolean result) {

    }

    @Override
    public void addUserToGameDataComServ(Game gameNewPlayer, Player newPlayer, UUID idUser) {

    }

    @Override
    public void modify(ServerProfile profile) {

    }

    @Override
    public void sendUpdateRoundAndEndResult(Round round, ArrayList<Result> results, List<UUID> players) {

    }

    @Override
    public void sendNewRound(Round round, Round newRound, List<UUID> players) {

    }

    @Override
    public void saveUser(ServerProfile newUser) {
        dataServerCore.getConnectedPlayers().add(newUser);
    }

    @Override
    public ArrayList<Game> getWaitingGames() {
        return dataServerCore.getUnfilledWaitingGames();
    }

    @Override
    public ArrayList<ServerProfile> getConnectedPlayers() {
        return dataServerCore.getConnectedPlayers();
    }

    @Override
    public void initGameServer(Game newGame) {
        dataServerCore.getWaitingGames().add(newGame);
    }

    @Override
    public void askJoinTableComDataServ(UUID idUser, UUID idGame) {
        if (checkJoiningConditions(idUser, idGame) == true) {
            ServerProfile player = dataServerCore.getConnectedPlayer(idUser);
            Game game = dataServerCore.getUnfilledWaitingGame(idGame);
            if(game != null) {
                game.getPlayers().add(player);
                dataServerCore.getiDataCallsCom().joinTableRequestDataComServ(idUser, idGame);
            }
        }
    }

    /**
     * Returns true if a player can join the game
     * @param idUser : id of the user who wants to join the game
     * @param idGame : id of the game to be joined
     * @return
     */
    private boolean checkJoiningConditions(UUID idUser, UUID idGame){
        Game game = dataServerCore.getWaitingGame(idGame);
        //check that the game exists
        if(game == null)
            return false;
        //checks that the maximum number of players has not been reached
        if(game.getNbMaxPlayers() == game.getPlayers().size())
            return false;
        Player player = dataServerCore.getPlayerInGame(idUser, game);
        //checks that the player is not already in the game
        if(player != null)
            return false;
        return true;
    }
}