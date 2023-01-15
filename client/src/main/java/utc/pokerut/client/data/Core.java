package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;

public class Core {

    private IHMMainCallsData ihmMainCallsData;
    private IHMGameCallsData ihmGameCallsData;
    private DataCallsIHMMain iDataCallsIHMMain;
    private DataCallsIHMGame iDataCallsIHMGame;
    private ComCallsData comCallsData;
    private DataCallsCom iDataCallsCom;
    private ClientProfile profile;
    private Game currentGame;
    private ArrayList<Player> connectedPlayers;
    private ArrayList<Game> waitingGame;
    private PropertyChangeSupport pcsGame;
    private PropertyChangeSupport pcsPlayer;

    public Core() {
        ihmMainCallsData  = new IHMMainCallsDataClientImpl(this);
        comCallsData      = new ComCallsDataClientImpl(this);
        ihmGameCallsData  = new IHMGameCallsDataClientImpl(this);
        waitingGame       = new ArrayList<Game>();
        connectedPlayers  = new ArrayList<Player>();
        pcsGame           = new PropertyChangeSupport(waitingGame);
        pcsPlayer         = new PropertyChangeSupport(connectedPlayers);
    }

    /**
     *
     * @return
     */
    public IHMMainCallsData getIhmMainCallsData() {
        return ihmMainCallsData;
    }

    /**
     *
     * @return
     */
    public IHMGameCallsData getIhmGameCallsData() {
        return ihmGameCallsData;
    }

    /**
     *
     * @return
     */
    public PropertyChangeSupport getPcsGame() {
        return pcsGame;
    }

    /**
     *
     * @return
     */
    public PropertyChangeSupport getPcsPlayer() {
        return pcsPlayer;
    }

    /**
     *
     * @return
     */
    public DataCallsCom getiDataCallsCom() {
        return iDataCallsCom;
    }

    /**
     *
     * @return
     */
    public ComCallsData getComCallsData() {
        return comCallsData;
    }

    /**
     *
     * @param iDataCallsCom
     */
    public void setiDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }

    /**
     *
     * @param ihmMainCallsData
     */
    public void setIhmMainCallsData(IHMMainCallsData ihmMainCallsData) {
        this.ihmMainCallsData = ihmMainCallsData;
    }

    /**
     *
     * @param listener
     */
    public void addPropertyChangeListenerGame(PropertyChangeListener listener) {
        this.pcsGame.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void addPropertyChangeListenerPlayer(PropertyChangeListener listener) {
        this.pcsPlayer.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListenerGame(PropertyChangeListener listener) {
        this.pcsGame.removePropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListenerPlayer(PropertyChangeListener listener) {
        this.pcsPlayer.removePropertyChangeListener(listener);
    }

    /**
     *
     * @return
     */
    public ClientProfile getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(ClientProfile profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public Game getCurrentGame() {
        return currentGame;
    }

    /**
     *
     * @param currentGame
     */
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getConnectedPlayers() {
        return connectedPlayers;
    }

    /**
     *
     * @return
     */
    public ArrayList<Game> getWaitingGame() {
        return waitingGame;
    }

    /**
     *
     * @param connectedPlayers
     */
    public void setConnectedPlayers(ArrayList<Player> connectedPlayers) {
        ArrayList<Player> oldConnectedPlayers = this.connectedPlayers;
        this.connectedPlayers = connectedPlayers;
        this.pcsPlayer.firePropertyChange("init_connectedPlayers", oldConnectedPlayers, this.connectedPlayers);
    }

    /**
     *
     * @param waitingGame
     */
    public void setWaitingGame(ArrayList<Game> waitingGame) {
        ArrayList<Game> oldWaitingGame = this.waitingGame;
        this.waitingGame = waitingGame;
        this.pcsGame.firePropertyChange("init_waitingGame", oldWaitingGame, this.waitingGame);
    }

    /**
     *
     * @param newGame
     */
    public void addWaitingGame(Game newGame) {
        this.waitingGame.add(newGame);
        this.pcsGame.firePropertyChange("add_waitingGame", this.waitingGame, newGame);
    }

    /**
     *
     * @param newPlayer
     */
    public void addNewPlayer(Player newPlayer) {
        this.connectedPlayers.add(newPlayer);
        System.out.println("addNewPlayer");
        this.pcsPlayer.firePropertyChange("add_connectedPlayers", this.connectedPlayers, newPlayer);
    }

    /**
     *
     * @param game
     */
    public void removeWaitingGame(Game game) {
        this.waitingGame.remove(game);
        this.pcsGame.firePropertyChange("remove_waitingGame", this.waitingGame, game);
    }

    /**
     *
     * @param player
     */
    public void removePlayer(Player player) {
        this.connectedPlayers.remove(player);
        this.pcsPlayer.firePropertyChange("remove_connectedPlayer", this.connectedPlayers, player);
    }

    /**
     *
     * @return
     */
    public DataCallsIHMMain getiDataCallsIHMMain() {
        return this.iDataCallsIHMMain;
    }

    /**
     *
     * @return
     */
    public DataCallsIHMGame getiDataCallsIHMGame() {
        return this.iDataCallsIHMGame;
    }

    /**
     *
     * @param iDataCallsIHMGame
     */
    public void setiDataCallsIHMGame(DataCallsIHMGame iDataCallsIHMGame) {
        this.iDataCallsIHMGame = iDataCallsIHMGame;
    }

    /**
     *
     * @param iDataCallsIHMMain
     */
    public void setiDataCallsIHMMain(DataCallsIHMMain iDataCallsIHMMain) {
        this.iDataCallsIHMMain = iDataCallsIHMMain;
    }

    /**
     *
     * @param comCallsData
     */
    public void setComCallsData(ComCallsDataClientImpl comCallsData) {
        this.comCallsData = comCallsData;
	}

    /**
     *
     * @param playerId
     * @return
     */
    public Player getConnectedPlayer(UUID playerId) {
        Player connectedPlayer = connectedPlayers.stream().filter(player -> player.getId().equals(playerId)).findFirst().orElse(null);
        return connectedPlayer;
    }

}
