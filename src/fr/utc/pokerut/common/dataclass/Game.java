package src.fr.utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class Game implements Serializable {
    private UUID id;
    private String name;
    private int nbMaxPlayers;
    private int nbPoints;
    private int nbRounds;
    private boolean chat;
    private ArrayList<Player> players;
    private Player creator;
    private ArrayList<Result> results;
    private StatusEnum status;
    private ArrayList<Round> rounds;

    private Round currentRound;

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

    public int getNbRounds() {
        return nbRounds;
    }

    public void setNbRounds(int nbRounds) {
        this.nbRounds = nbRounds;
    }

    public boolean isChat() {
        return chat;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
        this.results = results;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }
}
