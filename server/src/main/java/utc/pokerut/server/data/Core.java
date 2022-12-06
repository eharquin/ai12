package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class Core {


    // private ComCallDataServerImpl iComServerCallDataServerImpl;
    //private DataServerCallComServer iDataServerCallComServer; // à déclarer dans common/interface
    private ArrayList<GameEngine> onGoingGames;
    private ArrayList<Game> waitingGames;
    private ArrayList<ServerProfile> connectedPlayers;

    public Game getOnGoingGame(UUID gameId) {
        Game game = onGoingGames.stream().filter(a -> a.getGame().getId() == gameId).collect(Collectors.toList()).get(0).getGame();
        return game;
    }

    public GameEngine getOnGoingGameEngine(UUID gameId) {
        GameEngine game = onGoingGames.stream().filter(a -> a.getGame().getId() == gameId).collect(Collectors.toList()).get(0);
        return game;
    }


    public ArrayList<GameEngine> getOnGoingGames() {
        return onGoingGames;
    }

    public void setOnGoingGames(ArrayList<GameEngine> onGoingGames) {
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
    public ServerProfile getConnectedPlayer(UUID playerId) {
        ServerProfile connectedPlayer = connectedPlayers.stream().filter(player -> player.getId() == playerId).findFirst().orElse(null);
        return connectedPlayer;
    }
    public void removeConnectedPlayer(UUID playerDisconnectingId) {
        Player player = getConnectedPlayer(playerDisconnectingId);
        connectedPlayers.remove(player);
    }
}
