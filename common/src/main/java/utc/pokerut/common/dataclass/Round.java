package utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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
    private int nbActivePlayers;
    private int nbCallSuccessivePlayers;
    private int nbCheckSuccessivePlayers;

    public Round(){
        this.setCurrentBettingRound(1);
        this.setCurrentBet(0); // 0, le premier joueur doit payer la petite blinde
        this.setActions(new ArrayList<>());
        this.setHands(new ArrayList<>());
        this.setShowedCards(new ArrayList<>());
        CardDeck cardDeck = new CardDeck();
        this.setCards(cardDeck.getCardDeck());
        this.setCurrentBets(new HashMap<>());
    }
    public Round(Player firstPlayer){
        this.setCurrentPlayer(firstPlayer);
        this.setCurrentBettingRound(1);
        this.setCurrentBet(0); // 0, le premier joueur doit payer la petite blinde
        this.setActions(new ArrayList<>());
        this.setHands(new ArrayList<>());
        this.setShowedCards(new ArrayList<>());
        CardDeck cardDeck = new CardDeck();
        this.setCards(cardDeck.getCardDeck());
        this.setCurrentBets(new HashMap<>());
    }

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

    /**
     * tour de bet en cours, ce sur quoi on suit où on relance
     * @param currentBet
     */
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

    public Hand getHandByPlayerId(UUID playerId) {
        Hand hand = hands.stream().filter(hands -> hands.getPlayer().getId() == playerId).findAny().orElse(null);
        return hand;
    }

    public int getNbActivePlayers() {
        return nbActivePlayers;
    }

    public void setNbActivePlayers(int nbActivePlayers) {
        this.nbActivePlayers = nbActivePlayers;
    }

    public int getNbCallSuccessivePlayers() {
        return nbCallSuccessivePlayers;
    }

    public void setNbCallSuccessivePlayers(int nbCallSuccessivePlayers) {
        this.nbCallSuccessivePlayers = nbCallSuccessivePlayers;
    }

    public int getNbCheckSuccessivePlayers() {
        return nbCheckSuccessivePlayers;
    }

    public void setNbCheckSuccessivePlayers(int nbCheckSuccessivePlayers) {
        this.nbCheckSuccessivePlayers = nbCheckSuccessivePlayers;
    }
}
