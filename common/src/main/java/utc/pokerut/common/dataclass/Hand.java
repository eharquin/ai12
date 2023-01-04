package utc.pokerut.common.dataclass;

import java.util.ArrayList;

public class Hand {
    public static final int NB_CARDS_IN_HAND = 2;
    private Player player;
    private Round round;
    private int availablePoints;
    private int bet1;
    private int bet2;
    private int bet3;
    private int bet4;
    private boolean isFold;

    private int valueWinComb;
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

    public Hand (){
        this.availablePoints = 0;
        this.bet1 = 0;
        this.bet2 = 0;
        this.bet3 = 0;
        this.bet4 = 0;
    }

    public Hand(Player player, Round round, ArrayList<Card> cards) {
        this.player = player;
        this.round = round;
        this.availablePoints = 0;
        this.bet1 = 0;
        this.bet2 = 0;
        this.bet3 = 0;
        this.bet4 = 0;
        this.cards = cards;
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



    public int getBet1() {
        return bet1;
    }

    public void setBet1(int bet1) {
        this.bet1 = bet1;
    }

    public int getBet2() {
        return bet2;
    }

    public void setBet2(int bet2) {
        this.bet2 = bet2;
    }

    public int getBet3() {
        return bet3;
    }

    public void setBet3(int bet3) {
        this.bet3 = bet3;
    }

    public int getBet4() {
        return bet4;
    }

    public void setBet4(int bet4) {
        this.bet4 = bet4;
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

    public int getValueWinComb() {
        return valueWinComb;
    }

    public void setValueWinComb(int valueWinComb) {
        this.valueWinComb = valueWinComb;
    }
}
