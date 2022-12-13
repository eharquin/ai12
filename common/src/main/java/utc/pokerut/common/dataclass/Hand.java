package utc.pokerut.common.dataclass;

import java.util.ArrayList;

public class Hand {
    private Player player;
    private Round round;
    private int availablePoints;
    private boolean isFold;
    private ArrayList<Card> cards;

    public Hand(){
        player = null;
        round = null;
        availablePoints = 0;
        isFold = false;
        cards = new ArrayList<>();
    }

    public Hand(Round round, Player player, ArrayList<Card> cards, int availablePoints) {
        this.round = round;
        this.player = player;
        this.isFold = false;
        this.cards = cards;
        this.availablePoints = availablePoints;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(int availablePoints) {
        this.availablePoints = availablePoints;
    }

    public boolean getIsFold() {
        return isFold;
    }

    public void setIsFold(boolean isFold) {
        this.isFold = isFold;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
