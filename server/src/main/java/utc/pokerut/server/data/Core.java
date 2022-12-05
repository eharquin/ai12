package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Core {

    private DataCallsCom iDataCallsCom;
    // private ComCallDataServerImpl iComServerCallDataServerImpl;
    //private DataServerCallComServer iDataServerCallComServer; // à déclarer dans common/interface
    private ArrayList<Game> onGoingGames;
    private ArrayList<Game> waitingGames;
    private ArrayList<ServerProfile> connectedPlayers;
    private final GameEngine gameEngine = new GameEngine();

    public Player getPlayerInGame(UUID playerID, Game game) {
        Player player = game.getPlayers().stream().filter(a -> a.getId() == playerID).findAny().orElse(null);
        return player;
    }

    public ServerProfile getConnectedPlayer(UUID playerID) {
        ServerProfile player = connectedPlayers.stream().filter(a -> a.getId() == playerID).findAny().orElse(null);
        return player;
    }
    public Game getWaitingGame(UUID gameId) {
        Game game = waitingGames.stream().filter(a -> a.getId() == gameId).findAny().orElse(null);
        return game;
    }

    public Game getOnGoingGame(UUID gameId) {
        Game game = onGoingGames.stream().filter(a -> a.getId() == gameId).findAny().orElse(null);
        return game;
    }

    public List<Game> getOnGoingGames() {
        return onGoingGames;
    }

    public void setOnGoingGames(ArrayList<Game> onGoingGames) {
        this.onGoingGames = onGoingGames;
    }

    public ArrayList<Game> getWaitingGames() {
        return waitingGames;
    }

    public void setWaitingGames(ArrayList<Game> waitingGames) {
        this.waitingGames = waitingGames;
    }

    public ArrayList<ServerProfile> getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(ArrayList<ServerProfile> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public DataCallsCom getiDataCallsCom() {
        return iDataCallsCom;
    }

    public void setiDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }
}
