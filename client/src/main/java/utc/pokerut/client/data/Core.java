package utc.pokerut.client.data;


import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.DataCallsIHMMain;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;
import utc.pokerut.common.interfaces.server.DataCallsCom;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class Core {
    private ComCallsDataClientImpl iComCallsDataClientImpl;

    public IHMMainCallsData getIhmMainCallsData() {
        return ihmMainCallsData;
    }

    public void setIhmMainCallsData(IHMMainCallsData ihmMainCallsData) {
        this.ihmMainCallsData = ihmMainCallsData;
    }

    private IHMMainCallsData ihmMainCallsData;

    private DataCallsIHMMain iDataCallsIHMMain;

    public DataCallsCom getiDataCallsCom() {
        return iDataCallsCom;
    }

    public ComCallsDataClientImpl getiComCallsDataClientImpl() {
        return iComCallsDataClientImpl;
    }

    public void setiDataCallsCom(DataCallsCom iDataCallsCom) {
        this.iDataCallsCom = iDataCallsCom;
    }

    private DataCallsCom iDataCallsCom;
    private ClientProfile profile;

    private Game currentGame;

    private ArrayList<Player> connectedPlayers;

    private ArrayList<Game> waitingGame;

    public PropertyChangeSupport getPcsGame() {
        return pcsGame;
    }

    private PropertyChangeSupport pcsGame;

    public PropertyChangeSupport getPcsPlayer() {
        return pcsPlayer;
    }

    private PropertyChangeSupport pcsPlayer;

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

    public Core()
    {
        ihmMainCallsData = new IHMMainCallsDataClientImpl(this);
        waitingGame = new ArrayList<Game>();
        connectedPlayers = new ArrayList<Player>();
        pcsGame = new PropertyChangeSupport(waitingGame);
        pcsPlayer = new PropertyChangeSupport(connectedPlayers);
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
    public void saveProfile(ClientProfile profile) throws Exception {

        String file_path =  profile.getId().toString() + ".ser";
        ObjectOutputStream oos = null;

        try {
            // on ouvre le fichier
            FileOutputStream file = new FileOutputStream(file_path);
            // on cree une instance d'un ObjectStream en utilisant le fichier ouvert
            oos = new ObjectOutputStream(file);
            // on sauvegarde le profile dans le fichier
            oos.writeObject(profile);
            // on push les binaires par le pipeline
            oos.flush();
        } catch (IOException e_write) {
            // erreur rencontre pendant l'ouverture et/ou sauvegarde
            throw new Exception(e_write);
        } finally {
            try {
                // fermeture du pipeline
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e_close) {
                // erreur rencontre pendant la fermeture du pipeline
                throw new Exception(e_close);
            }
        }
    }


    public DataCallsIHMMain getiDataCallsIHMMain() {
        return this.iDataCallsIHMMain;
    }

    public void setiDataCallsIHMMain(DataCallsIHMMain iDataCallsIHMMain) {
        this.iDataCallsIHMMain = iDataCallsIHMMain;
    }
}
