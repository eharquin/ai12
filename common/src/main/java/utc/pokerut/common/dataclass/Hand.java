package utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.util.ArrayList;

public class Hand implements Serializable {
    public static final int NB_CARDS_IN_HAND = 2;
    private Player player;
    private Round round;
    private int availablePoints;
    private int bet1;
    private int bet2;
    private int bet3;
    private int bet4;
    private int totalBet;
    private boolean isFold;
    private boolean isAllIn;

    private int valueWinComb;
    private ArrayList<Card> cards;

    public Hand (){
        this.availablePoints = 0;
        this.bet1 = 0;
        this.bet2 = 0;
        this.bet3 = 0;
        this.bet4 = 0;
    }

    public Hand(Player player, Round round, ArrayList<Card> cards, int availablePoints) {
        this.player = player;
        this.round = round;
        this.availablePoints = availablePoints;
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

    public int getValueWinComb() {
        return valueWinComb;
    }

    public void setValueWinComb(int valueWinComb) {
        this.valueWinComb = valueWinComb;
    }

    public int getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(int totalBet) {
        this.totalBet = totalBet;
    }

    public boolean isAllIn() {
        return isAllIn;
    }

    public void setAllIn(boolean allIn) {
        isAllIn = allIn;
    }
}
