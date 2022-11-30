package client.src.main.java.utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class DataClientCore {
    private ClientProfile profile;

    private Game currentGame;

    private ArrayList<Player> connectedPlayers;

    private ArrayList<Game> waitingGame;

    private final PropertyChangeSupport pcsGame = new PropertyChangeSupport(waitingGame);

    private final PropertyChangeSupport pcsPlayer = new PropertyChangeSupport(connectedPlayers);

    public void addPropertyChangeListenerGame(PropertyChangeListener listener) {
        this.pcsGame.addPropertyChangeListener(listener);
    }
    public void addPropertyChangeListenerPlayer(PropertyChangeListener listener) {
        this.pcsPlayer.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListenerGame(PropertyChangeListener listener) {
        this.pcsGame.removePropertyChangeListener(listener);
    }
    public void removePropertyChangeListenerPlayer(PropertyChangeListener listener) {
        this.pcsPlayer.removePropertyChangeListener(listener);
    }
    public ClientProfile getProfile() {
        return profile;
    }

    public void setProfile(ClientProfile profile) {
        this.profile = profile;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public ArrayList<Player> getConnectedPlayers() {
        return connectedPlayers;
    }
    public ArrayList<Game> getWaitingGame() {
        return waitingGame;
    }

    public void setConnectedPlayers(ArrayList<Player> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
        this.pcsPlayer.firePropertyChange("init_connectedPlayers", null, this.connectedPlayers);
    }

    public void setWaitingGame(ArrayList<Game> waitingGame) {
        this.waitingGame = waitingGame;
        this.pcsGame.firePropertyChange("init_waitingGame", null, this.waitingGame);
    }

    public void addWaitingGame(Game newGame){
        this.waitingGame.add(newGame);
        this.pcsGame.firePropertyChange("add_waitingGame", this.waitingGame, newGame);
    }

    public void addNewPlayer(Player newPlayer){
        this.connectedPlayers.add(newPlayer);
        this.pcsPlayer.firePropertyChange("add_connectedPlayers", this.connectedPlayers, newPlayer);
    }

    public void removeWaitingGame(Game game){
        this.waitingGame.remove(game);
        this.pcsGame.firePropertyChange("remove_waitingGame", this.waitingGame, game);
    }
    public void removePlayer(Player player){
        this.connectedPlayers.remove(player);
        this.pcsPlayer.firePropertyChange("remove_connectedPlayer", this.connectedPlayers, player);
    }
}