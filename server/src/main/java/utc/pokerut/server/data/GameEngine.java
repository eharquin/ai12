package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Card;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Hand;
import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.dataclass.Round;

import java.util.*;
import java.util.stream.Collectors;

public class GameEngine {

    public ArrayList<Result> getRanking() {
        ArrayList<Result> results = new ArrayList<>();
        return results;
    }

    public boolean isBettingPossible(Round round) {
        return (round.getCurrentBettingRound()!=1 && round.getCurrentBet()==0);
    }

    public boolean isCallingPossible(Round round){
        return round.getCurrentBet()!=0;
    }

    public boolean isRaisingPossible(Round round, Hand hand){
        int currentBet = round.getCurrentBet();
        return (currentBet!=0 && hand.getAvailablePoints()>currentBet);
    }

    public boolean isAllInPossible(Round round, Hand hand){
        int currentBet = round.getCurrentBet();
        return (currentBet!=0 && hand.getAvailablePoints()<currentBet);
    }

    public boolean isCheckingPossible(Round round){
        return (round.getCurrentBettingRound()!=1 && round.getCanCheck());
    }

    public boolean isFoldingPossible(Hand hand){
        return hand.getIsFold();
    }

    public List<Card> sortCards(List<Card> cardList){
        List<Card> sortedCards = cardList.stream()
                .sorted(Comparator.comparing(Card::getValue))
                .collect(Collectors.toList());
        return sortedCards;
    }

    public boolean isSameColor(List<Card> cardList){
        String symbol = cardList.get(0).getSymbol();
        boolean flag = true;
        int ind = 1;
        while (flag && ind < cardList.size()){
            flag = symbol==cardList.get(ind).getSymbol();
            ind++;
        }
        return flag;
    }

    public boolean isQuinte(List<Card> cardList){
        boolean flag = true;
        for (int i = 0; i < cardList.size()-1; i++){
            flag = flag && (cardList.get(i+1).getValue()==cardList.get(i).getValue()+1);
        }
        return flag;
    }

    public boolean isQuinteFlush(List<Card> cardList){
        return isSameColor(cardList);
    }

    public boolean isQuinteFlushRoyale(List<Card> cardList){
        return cardList.get(0).getValue()==10;
    }
    public HashMap<Integer, Integer> getMultiple(List<Card> cardList){
        HashMap<Integer, Integer> multiples = new HashMap<>();
        int initValue = cardList.get(0).getValue();
        multiples.put(initValue, 1);
        int ind = 1;

        while (ind <= 5){
            while(cardList.get(ind).getValue()==initValue){
                if (multiples.containsKey(initValue)){
                    multiples.replace(initValue, multiples.get(initValue)+1);
                }
                else {
                    multiples.put(initValue, 1);
                }
                ind++;
            }
            initValue = cardList.get(ind).getValue();
        }
        return multiples;
    }

    public static <Integer> List<Integer> getMultipleKeysByValue(HashMap<Integer, Integer> map, Integer value) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getValue()==value)
                .map(HashMap.Entry::getKey)
                .collect(Collectors.toList());
    }

    public String evalComb(List<Card> cardList){

        String comb = null;
        boolean quinteCheck = isQuinte(cardList);

        if (quinteCheck){
            if(isQuinteFlush(cardList)){
                comb = "Quinte Flush";
                if (isQuinteFlushRoyale(cardList)){
                    comb = "Quinte Flush Royale";
                }
                return comb; // no better combination
            }
            comb = "Quinte";
        }

        HashMap<Integer, Integer> multiples = getMultiple(cardList);

        if(multiples.containsValue(4)){
            comb = "Carré";
        } else {
            boolean brelan = multiples.containsValue(3);
            if (brelan && multiples.containsValue(2)) {
                    comb = "Full";
            } else if (isSameColor(cardList)) {
                comb = "Couleur";
            } else if(brelan) {
                comb = "Brelan";
            } else {
                List<Integer> doubles = getMultipleKeysByValue(multiples, 2);
                int nbDouble = doubles.size();
                switch (nbDouble){
                    case 2 :
                        comb = "Double paire";
                    case 1 :
                        comb = "Paire";
                    case 0 :
                        comb = "Carte haute";
                }
            }
        }

        return comb;
    }

    public int translateResults(){
        int points = 0;
        return points;
    }
}
