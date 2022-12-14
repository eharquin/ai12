package utc.pokerut.common.dataclass;

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
    private int currentBet;
    private int currentBettingRound;
    private boolean canCheck;
    private ArrayList<Card> showedCards;

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

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getCurrentBettingRound() {
        return currentBettingRound;
    }

    public void setCurrentBettingRound(int currentBettingRound) {
        this.currentBettingRound = currentBettingRound;
    }

    public boolean getCanCheck() {
        return canCheck;
    }

    public void setCanCheck(boolean canCheck) {
        this.canCheck = canCheck;
    }

    public ArrayList<Card> getShowedCards() {
        return showedCards;
    }

    public void setShowedCards(ArrayList<Card> showedCards) {
        this.showedCards = showedCards;
    }
}
