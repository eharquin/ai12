package utc.pokerut.client.data;


import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.DataCallsIHMGame;
import utc.pokerut.common.interfaces.client.DataCallsIHMMain;
import utc.pokerut.common.interfaces.client.IHMGameCallsData;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;
import utc.pokerut.common.interfaces.client.DataCallsCom;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class Core {
    private ComCallsDataClientImpl iComCallsDataClientImpl;
    private IHMMainCallsData ihmMainCallsData;
    private IHMGameCallsData ihmGameCallsData;
    private DataCallsIHMMain iDataCallsIHMMain;
    private DataCallsIHMGame iDataCallsIHMGame;
    private DataCallsCom iDataCallsCom;
    private ClientProfile profile;
    private Game currentGame;
    private ArrayList<Player> connectedPlayers;
    private ArrayList<Game> waitingGame;
    private PropertyChangeSupport pcsGame;
    private PropertyChangeSupport pcsPlayer;

    public Core()
    {
        ihmMainCallsData = new IHMMainCallsDataClientImpl(this);
        waitingGame = new ArrayList<Game>();
        connectedPlayers = new ArrayList<Player>();
        pcsGame = new PropertyChangeSupport(waitingGame);
        pcsPlayer = new PropertyChangeSupport(connectedPlayers);
    }
    public IHMMainCallsData getIhmMainCallsData() {
        return ihmMainCallsData;
    }

    public IHMGameCallsData getIhmGameCallsData() {
        return ihmGameCallsData;
    }

    public PropertyChangeSupport getPcsGame() {
        return pcsGame;
    }

    public PropertyChangeSupport getPcsPlayer() {
        return pcsPlayer;
    }

    public DataCallsCom getiDataCallsCom() {
        return iDataCallsCom;
    }

    public ComCallsDataClientImpl getiComCallsDataClientImpl() {
        return iComCallsDataClientImpl;
    }

    public void setiDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }

    public void setIhmMainCallsData(IHMMainCallsData ihmMainCallsData) {
        this.ihmMainCallsData = ihmMainCallsData;
    }

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
        ArrayList<Player> oldConnectedPlayers = this.connectedPlayers;
        this.connectedPlayers = connectedPlayers;
        this.pcsPlayer.firePropertyChange("init_connectedPlayers", oldConnectedPlayers, this.connectedPlayers);
    }

    public void setWaitingGame(ArrayList<Game> waitingGame) {
        ArrayList<Game> oldWaitingGame = this.waitingGame;
        this.waitingGame = waitingGame;
        this.pcsGame.firePropertyChange("init_waitingGame", oldWaitingGame, this.waitingGame);
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
    public DataCallsIHMMain getiDataCallsIHMMain() {
        return this.iDataCallsIHMMain;
    }

    public DataCallsIHMGame getiDataCallsIHMGame() {
        return this.iDataCallsIHMGame;
    }

    public void setiDataCallsIHMGame(DataCallsIHMGame iDataCallsIHMGame) {
        this.iDataCallsIHMGame = iDataCallsIHMGame;
    }

    public void setiDataCallsIHMMain(DataCallsIHMMain iDataCallsIHMMain) {
        this.iDataCallsIHMMain = iDataCallsIHMMain;
    }

    public void setiComCallsDataClientImpl(ComCallsDataClientImpl iComCallsDataClientImpl) {
        this.iComCallsDataClientImpl = iComCallsDataClientImpl;
    }
}
