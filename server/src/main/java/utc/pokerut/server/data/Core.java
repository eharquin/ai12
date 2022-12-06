package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.server.ComCallsData;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class Core {


    // private ComCallDataServerImpl iComServerCallDataServerImpl;
    //private DataServerCallComServer iDataServerCallComServer; // à déclarer dans common/interface
    private ArrayList<GameEngine> onGoingGames = new ArrayList<>();
    private ArrayList<Game> waitingGames = new ArrayList<>();
    private ArrayList<ServerProfile> connectedPlayers = new ArrayList<>();
    private ComCallsData comCallsData;
    private DataCallsCom iDataCallsCom;
    public Core()
    {
        comCallsData = new ComCallsDataServerImpl(this);
    }
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

    public ComCallsData getComCallsData() {
        return comCallsData;
    }

    public DataCallsCom getiDataCallsCom() {
        return iDataCallsCom;
    }
    public void setDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }
}
