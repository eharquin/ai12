package utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Round implements Serializable {
    private ArrayList<Action> actions;
    private ArrayList<Hand> hands;
    private LinkedList<Card> cards;
    private Player currentPlayer;
    private int currentBet;
    private int currentBettingRound;
    private boolean canCheck;
    private ArrayList<Card> showedCards;

    private final int NB_CARDS_HAND = 2;

    public Round(){
        this.setCurrentBettingRound(1);
        this.setCurrentBet(0); // 0, le premier joueur doit payer la petite blinde
        this.setActions(new ArrayList<>());
        this.setHands(new ArrayList<>());
        this.setShowedCards(new ArrayList<>());
        CardDeck cardDeck = new CardDeck();
        this.setCards(cardDeck.getCardDeck());
    }
    public Round(ArrayList<Player> players, int availablePoints){
        this.setCurrentPlayer(players.get(0));
        this.setCurrentBettingRound(1);
        this.setCurrentBet(0); // 0, le premier joueur doit payer la petite blinde
        this.setActions(new ArrayList<>());
        this.setHands(new ArrayList<>());
        this.setShowedCards(new ArrayList<>());
        CardDeck cardDeck = new CardDeck();
        this.setCards(cardDeck.getCardDeck());

        for(Player p : players)  {
            ArrayList<Card> handCards = new ArrayList<>();

            // on ajoute les cartes à la main
            for(int i =0; i <NB_CARDS_HAND; i++) {
                handCards.add(cards.getFirst());
                cards.getFirst();
            }

            Hand h = new Hand(this, p,handCards, availablePoints);
            this.hands.add(h);
        }

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

    public LinkedList<Card> getCards() {
        return cards;
    }

    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
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
        Hand hand = hands.stream().filter(h -> h.getPlayer().getId() == playerId).findAny().orElse(null);
        return hand;
    }
}
