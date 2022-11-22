package fr.utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Round implements Serializable {
    private ArrayList<Action> actions;
    private ArrayList<Hand> hands;
    private ArrayList<Card> cards;
    private HashMap<Player, Integer> currentBets;
    private Player currentPlayer;
    private int currentBettingRound;

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public void setHands(ArrayList<Hand> hands) {
        this.hands = hands;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public HashMap<Player, Integer> getCurrentBets() {
        return currentBets;
    }

    public void setCurrentBets(HashMap<Player, Integer> currentBets) {
        this.currentBets = currentBets;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentBettingRound() {
        return currentBettingRound;
    }

    public void setCurrentBettingRound(int currentBettingRound) {
        this.currentBettingRound = currentBettingRound;
    }
}
