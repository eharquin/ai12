package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.Round;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.server.ComCallsData;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Core {

    private DataCallsCom iDataCallsCom;
    private ArrayList<Game> onGoingGames = new ArrayList<>();
    private ArrayList<Game> waitingGames = new ArrayList<>();
    private ArrayList<ServerProfile> connectedPlayers = new ArrayList<>();
    private final GameEngine gameEngine = new GameEngine();
    private ComCallsData comCallsData;

    public Core()
    {
        comCallsData = new ComCallsDataServerImpl(this);
    }

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

    public ArrayList<Game> getUnfilledWaitingGames() {
        ArrayList<Game> games = (ArrayList<Game> ) waitingGames.stream().filter(game -> game.getPlayers().size() < game.getNbMaxPlayers())
                .collect(Collectors.toList());
        if(games == null)
            games = new ArrayList<>();
        return games;
    }

    public Game getUnfilledWaitingGame(UUID gameId) {
        Game unfilledGame = waitingGames.stream().filter(game -> game.getId() == gameId && game.getPlayers().size() < game.getNbMaxPlayers())
                .findAny().orElse(null);
        return unfilledGame;
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
    public ComCallsData getComCallsData() {
        return comCallsData;
    }
    public void setDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }

    public Player getNextPlayers(List<Player> players, UUID currentPlayerId){
        for(int i=0; i<players.size(); i++) {
            if(players.get(i).getId() == currentPlayerId)
                return players.get((i+1)%players.size());
        }
        return null;
    }

    public void setNextPlayerRound(List<Player> players, Round round){
        for(int i=0; i<players.size(); i++) {
            if(players.get(i).getId() == round.getCurrentPlayer().getId()) {
                round.setCurrentPlayer(players.get((i+1)%players.size()));
                return ;
            }
        }

    }
}
