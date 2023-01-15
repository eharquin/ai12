package utc.pokerut.common.dataclass;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Game implements Serializable {

    private UUID id;
    private String name;
    private int nbMaxPlayers;
    private int nbPoints;
    private int minimalBet;
    private int nbRounds;
    private boolean chatStatus;
    private ArrayList<Player> players;
    private Player creator;
    private ArrayList<Result> results;
    private StatusEnum status;
    private ArrayList<Round> rounds;
    private Round currentRound;
    private Chat chat;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     *
     * @param name
     * @param nbMaxPlayers
     * @param nbPoints
     * @param minimalBet
     * @param nbRounds
     */
    public Game(String name, int nbMaxPlayers, int nbPoints, int minimalBet, int nbRounds) {
        this.name          = name;
        this.nbMaxPlayers  = nbMaxPlayers;
        this.nbPoints      = nbPoints;
        this.minimalBet    = minimalBet;
        this.nbRounds      = nbRounds;
        this.id            = UUID.randomUUID();
        this.players       = new ArrayList<>();
        this.rounds        = new ArrayList<Round>();
    }

    /**
     *
     * @param listener
     */
    public void addPropertyChangeListenerPCS(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListenerPCS(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     *
     * @return
     */
    public PropertyChangeSupport getPcs() {
        return pcs;
    }

    /**
     *
     * @return
     */
    public Chat getChat() {
        return chat;
    }

    /**
     *
     * @param chat
     */
    public void setChat(Chat chat) {
        this.chat = chat;
        this.pcs.firePropertyChange("init_chat", null, this.chat);
    }

    /**
     *
     * @param chat
     */
    public void updateChat(Chat chat) {
        Chat oldChat = this.chat;
        this.chat = chat;
        this.pcs.firePropertyChange("update_chat", oldChat, this.chat);
    }

    /**
     *
     * @return
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getNbMaxPlayers() {
        return nbMaxPlayers;
    }

    /**
     *
     * @param nbMaxPlayers
     */
    public void setNbMaxPlayers(int nbMaxPlayers) {
        this.nbMaxPlayers = nbMaxPlayers;
    }

    /**
     *
     * @return
     */
    public int getNbPoints() {
        return nbPoints;
    }

    /**
     *
     * @param nbPoints
     */
    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    /**
     *
     * @return
     */
    public int getMinimalBet() {
        return minimalBet;
    }

    /**
     *
     * @param minimalBet
     */
    public void setMinimalBet(int minimalBet) {
        this.minimalBet = minimalBet;
    }

    /**
     *
     * @return
     */
    public int getNbRounds() {
        return nbRounds;
    }

    /**
     *
     * @param nbRounds
     */
    public void setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
    }

    /**
     *
     * @return
     */
    public boolean isChatStatus() {
        return chatStatus;
    }

    /**
     *
     * @param chatStatus
     */
    public void setChatStatus(boolean chatStatus) {
        this.chatStatus = chatStatus;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     *
     * @param players
     */
    public void setPlayers(ArrayList<Player> players) {
        ArrayList<Player> oldPlayers = this.players;
        this.players = players;
        this.pcs.firePropertyChange("init_players", oldPlayers, this.players);
    }

    /**
     *
     * @param newPlayer
     */
    public void addPlayer(Player newPlayer) {
        this.players.add(newPlayer);
        this.pcs.firePropertyChange("add_players", this.players, newPlayer);
    }

    /**
     *
     * @param player
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
        this.pcs.firePropertyChange("remove_players", this.players, player);
    }

    /**
     *
     * @return
     */
    public Player getCreator() {
        return creator;
    }

    /**
     *
     * @param creator
     */
    public void setCreator(Player creator) {
        this.creator = creator;
    }

    /**
     *
     * @return
     */
    public ArrayList<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     */
    public void setResults(ArrayList<Result> results) {
        ArrayList<Result> oldResults = this.results;
        this.results = results;
        this.pcs.firePropertyChange("init_results", oldResults, this.results);
    }

    /**
     *
     * @param newResult
     */
    public void addResult(Result newResult) {
        this.results.add(newResult);
        this.pcs.firePropertyChange("add_results", this.results, newResult);
    }

    /**
     *
     * @param result
     */
    public void removeResult(Result result) {
        this.results.remove(result);
        this.pcs.firePropertyChange("remove_results", this.results, result);
    }

    /**
     *
     * @return
     */
    public StatusEnum getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(StatusEnum status) {
        this.status = status;
        this.pcs.firePropertyChange("init_status", null, this.status);
    }

    /**
     *
     * @param newStatus
     */
    public void updateStatus(StatusEnum newStatus) {
        StatusEnum oldStatus = this.status;
        this.status = newStatus;
        this.pcs.firePropertyChange("update_status", oldStatus, this.status);
    }

    /**
     *
     * @return
     */
    public ArrayList<Round> getRounds() {
        return rounds;
    }

    /**
     *
     * @param rounds
     */
    public void setRounds(ArrayList<Round> rounds) {
        ArrayList<Round> oldRounds = this.rounds;
        this.rounds = rounds;
        this.pcs.firePropertyChange("init_rounds", oldRounds, this.rounds);
    }

    /**
     *
     * @param newRound
     */
    public void addRound(Round newRound) {
        this.rounds.add(newRound);
        this.pcs.firePropertyChange("add_rounds", this.rounds, newRound);
    }

    /**
     *
     * @return
     */
    public Round getCurrentRound() {
        return currentRound;
    }

    /**
     *
     * @param currentRound
     */
    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
        this.pcs.firePropertyChange("init_currentRound", null, this.currentRound);
    }

    /**
     *
     * @param newRound
     */
    public void updateCurrentRound(Round newRound) {
        Round oldRound = this.currentRound;
        this.currentRound = newRound;
        this.pcs.firePropertyChange("update_currentRound", oldRound, this.currentRound);
    }

    /**
     *
     * @param round
     */
    public void endGame(Round round) {
        Round oldRound = this.currentRound;
        this.currentRound = round;
        this.pcs.firePropertyChange("end_game", oldRound, this.currentRound);
    }
    
}
