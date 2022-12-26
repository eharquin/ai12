package utc.pokerut.common.dataclass;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Game implements Serializable {
    public static final int NB_MAX_ROUND = 4;
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

    public Game(String name, int nbMaxPlayers, int nbPoints, int minimalBet, int nbRounds) {
        this.name = name;
        this.nbMaxPlayers = nbMaxPlayers;
        this.nbPoints = nbPoints;
        this.minimalBet = minimalBet;
        this.nbRounds = nbRounds;
        this.id = UUID.randomUUID();
    }

    public void addPropertyChangeListenerPCS(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListenerPCS(PropertyChangeListener listener){
        this.pcs.removePropertyChangeListener(listener);
    }
    public PropertyChangeSupport getPcs(){
        return pcs;
    }
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        this.pcs.firePropertyChange("init_chat", null, this.chat);
    }
    public void updateChat(Chat chat){
        Chat oldChat = this.chat;
        this.chat = chat;
        this.pcs.firePropertyChange("update_chat", oldChat, this.chat);
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbMaxPlayers() {
        return nbMaxPlayers;
    }

    public void setNbMaxPlayers(int nbMaxPlayers) {
        this.nbMaxPlayers = nbMaxPlayers;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public int getMinimalBet() {
        return minimalBet;
    }

    public void setMinimalBet(int minimalBet) {
        this.minimalBet = minimalBet;
    }

    public int getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
    }

    public boolean isChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(boolean chatStatus) {
        this.chatStatus = chatStatus;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        ArrayList<Player> oldPlayers = this.players;
        this.players = players;
        this.pcs.firePropertyChange("init_player", oldPlayers, this.players);
    }
    public void addPlayer(Player newPlayer){
        this.players.add(newPlayer);
        this.pcs.firePropertyChange("add_players", this.players, newPlayer);
    }
    public void removePlayer(Player player){
        this.players.remove(player);
        this.pcs.firePropertyChange("remove_players", this.players, player);
    }

    public Player getCreator() {
        return creator;
    }

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        ArrayList<Result> oldResults = this.results;
        this.results = results;
        this.pcs.firePropertyChange("init_results", oldResults, this.results);
    }
    public void addResult(Result newResult){
        this.results.add(newResult);
        this.pcs.firePropertyChange("add_results", this.results, newResult);
    }
    public void removeResult(Result result){
        this.results.remove(result);
        this.pcs.firePropertyChange("remove_results", this.results, result);
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
        this.pcs.firePropertyChange("init_status", null, this.status);
    }
    public void updateStatus(StatusEnum newStatus){
        StatusEnum oldStatus = this.status;
        this.status = newStatus;
        this.pcs.firePropertyChange("update_status", oldStatus, this.status);
    }
    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        ArrayList<Round> oldRounds = this.rounds;
        this.rounds = rounds;
        this.pcs.firePropertyChange("init_rounds", oldRounds, this.rounds);
    }
    public void addRound(Round newRound){
        this.rounds.add(newRound);
        this.pcs.firePropertyChange("add_rounds", this.rounds, newRound);
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
        this.pcs.firePropertyChange("init_currentRound", null, this.currentRound);
    }
    public void updateCurrentRound(Round newRound){
        Round oldRound = this.currentRound;
        this.currentRound = newRound;
        this.pcs.firePropertyChange("update_currentRound", oldRound, this.currentRound);
    }
    public void endGame(Round round){
        Round oldRound = this.currentRound;
        this.currentRound = round;
        this.pcs.firePropertyChange("end_game", oldRound, this.currentRound);
    }
}
