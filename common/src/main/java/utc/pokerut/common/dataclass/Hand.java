package utc.pokerut.common.dataclass;

import java.util.ArrayList;

public class Hand {
    private Player player;
    private Round round;
    private int availablePoints;
    private boolean isFold;
    private ArrayList<Card> cards;
    private int bettingRound1;
    private int bettingRound2;
    private int bettingRound3;
    public Hand(){
        player = null;
        round = null;
        availablePoints = 0;
        isFold = false;
        cards = new ArrayList<>();
        this.bettingRound1 = 0;
        this.bettingRound2 = 0;
        this.bettingRound3 = 0;
    }

    public Hand(Round round, Player player, ArrayList<Card> cards, int availablePoints) {
        this.round = round;
        this.player = player;
        this.isFold = false;
        this.cards = cards;
        this.availablePoints = availablePoints;
        this.bettingRound1 = 0;
        this.bettingRound2 = 0;
        this.bettingRound3 = 0;
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

    public boolean isFold() {
        return isFold;
    }

    public void setFold(boolean fold) {
        isFold = fold;
    }

    public int getBettingRound1() {
        return bettingRound1;
    }

    public void setBettingRound1(int bettingRound1) {
        this.bettingRound1 = bettingRound1;
    }

    public int getBettingRound2() {
        return bettingRound2;
    }

    public void setBettingRound2(int bettingRound2) {
        this.bettingRound2 = bettingRound2;
    }

    public int getBettingRound3() {
        return bettingRound3;
    }

    public void setBettingRound3(int bettingRound3) {
        this.bettingRound3 = bettingRound3;
    }
}
