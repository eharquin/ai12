package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;

import java.util.*;
import java.util.stream.Collectors;

public class GameEngine {

    private final int STRAIGHT_FLUSH_SEIL = 1479685;
    private final int STRAIGHT_SEIL       = 1110938;
    private final int QUADS_SEIL          = 1479674;
    private final int FULL_SEIL           = 1479661;
    private final int FLUSH_SEIL          = 1479648;
    private final int SET_SEIL            = 1110928;
    private final int TWO_PAIRS_SEIL      = 1110915;
    private final int PAIR_SEIL           = 739807;
    private final int HIGH_CARD_SEIL      = 368713;

    public ArrayList<Result> getRanking() {
        ArrayList<Result> results = new ArrayList<>();
        return results;
    }

    public boolean isBettingPossible(Round round) {
        return (round.getCurrentBettingRound()!=1 && round.getCurrentBet()==0);
    }

    public boolean isCallingPossible(Round round) {
        return round.getCurrentBet()!=0;
    }

    public boolean isRaisingPossible(Round round, Hand hand) {
        int currentBet = round.getCurrentBet();
        return (currentBet!=0 && hand.getAvailablePoints()>currentBet);
    }

    public boolean isAllInPossible(Round round, Hand hand) {
        int currentBet = round.getCurrentBet();
        return (currentBet!=0 && hand.getAvailablePoints()<currentBet);
    }

    public boolean isCheckingPossible(Round round) {
        return (round.getCurrentBettingRound()!=1 && round.getCanCheck());
    }

    public boolean isFoldingPossible(Hand hand) {
        return hand.getIsFold();
    }

    public List<ActionTypeEnum> actionCalculation(Round round, Hand hand) {
        List<ActionTypeEnum> possibleActions = new ArrayList<ActionTypeEnum>();
        if (isBettingPossible(round))
            possibleActions.add(ActionTypeEnum.BET);
        if (isCallingPossible(round))
            possibleActions.add(ActionTypeEnum.CALL);
        if(isRaisingPossible(round, hand))
            possibleActions.add(ActionTypeEnum.RAISE);
        if(isCheckingPossible(round))
            possibleActions.add(ActionTypeEnum.CHECK);
        if(isFoldingPossible(hand))
            possibleActions.add(ActionTypeEnum.FOLD);
        if(isAllInPossible(round, hand))
            possibleActions.add(ActionTypeEnum.ALL_IN);
        return possibleActions;
    }

    public List<Card> sortCards(List<Card> cardList) {
        List<Card> sortedCards = cardList.stream()
                                         .sorted(Comparator.comparing(Card::getValue))
                                         .collect(Collectors.toList());
        return sortedCards;
    }

    public boolean isFlush(List<Card> cardList) {
        String symbol = cardList.get(0).getSymbol();
        boolean flag = true;
        int ind = 1;
        while (flag && ind < cardList.size()) {
            flag = symbol == cardList.get(ind).getSymbol();
            ind++;
        }
        return flag;
    }

    public boolean isStraight(List<Card> cardList) {
        boolean flag = true;
        for (int i = 0; i < cardList.size() - 1; i++) {
            flag = flag && (cardList.get(i+1).getValue() == cardList.get(i).getValue()+1);
        }
        return flag;
    }

    public boolean isStraightFlush(List<Card> cardList) {
        return isFlush(cardList);
    }

    public boolean isRoyalStraightFlush(List<Card> cardList) {
        return cardList.get(0).getValue() == 10;
    }

    public HashMap<Integer, Integer> getMultiple(List<Card> cardList) {
        HashMap<Integer, Integer> multiples = new HashMap<>();
        int initValue = cardList.get(0).getValue();
        multiples.put(initValue, 1);
        int ind = 1;

        while (ind <= 5){
            while(cardList.get(ind).getValue()==initValue) {
                if (multiples.containsKey(initValue)) {
                    multiples.replace(initValue, multiples.get(initValue) + 1);
                }
                else {
                    multiples.put(initValue,1);
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
                  .filter(entry -> entry.getValue() == value)
                  .map(HashMap.Entry::getKey)
                  .collect(Collectors.toList());
    }

    public int convertBase(List<Integer> values) {
        int ind = values.size();
        int convertedNumber = 0;
        while (ind > 0) {
            ind--;
            convertedNumber = convertedNumber * 13 + values.get(ind);
        }
        return convertedNumber;
    }

    public int evalComb(List<Card> cardList) {

        int result = 0;
        boolean straightCheck = isStraight(cardList);

        if (straightCheck) {
            if(isStraightFlush(cardList)) {
                result = QUADS_SEIL + cardList.get(0).getValue();                                    // straight flush, max = 1 479 684
                if (isRoyalStraightFlush(cardList)) {
                    result = STRAIGHT_FLUSH_SEIL;
                }
                return result;  // no better combination
            }
            result = SET_SEIL + cardList.get(0).getValue();                                          // straight, max = 1 110 938
        }

        HashMap<Integer, Integer> multiples = getMultiple(cardList);
        List<Integer> quadruplet = getMultipleKeysByValue(multiples, 4);
        List<Integer> triplet = getMultipleKeysByValue(multiples, 3);
        List<Integer> doubles = getMultipleKeysByValue(multiples, 2);
        List<Integer> single = getMultipleKeysByValue(multiples, 1);

        if(!quadruplet.isEmpty()) {
            result = FULL_SEIL + quadruplet.get(0);                                                  // quads, max = 1 479 674
        } else {
            boolean set = !triplet.isEmpty();
            if (set && !doubles.isEmpty()) {
                result = FLUSH_SEIL + triplet.get(0);                                                // full, max = 1 479 661
            } else if (isFlush(cardList)) {
                result = STRAIGHT_SEIL + convertBase(single);                                        // flush, max = 1 479 648
            } else if(set) {
                result = TWO_PAIRS_SEIL + triplet.get(0);                                            // set, max = 1 110 928
            } else {
                int nbDouble = doubles.size();
                switch (nbDouble) {
                    case 2 :
                        result = PAIR_SEIL + doubles.get(1) + doubles.get(0) + convertBase(single);  // two pairs, max = 1 110 915
                    case 1 :
                        result = HIGH_CARD_SEIL + doubles.get(0) + convertBase(single);              // pair, max = 739 807
                    case 0 :
                        result = convertBase(single);                                                // high card, max = 368 713
                }
            }
        }

        return result;
    }

    private List<List<Integer>> combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<List<Integer>> combs) {
        List<Integer> comb = new ArrayList<Integer>();
        if (index == r) {
            for (int j=0; j<r; j++) {
                comb.add(data[j]);
            }
            combs.add(comb);
            return combs;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr,data,i+1,end,index+1,r,combs);
        }
        return combs;
    }

    private List<Card> getCombination(int arr[], int n, int r, List<Card> allCards) {
        int data[] = new int[r];
        List<Card> winningComb = new ArrayList<Card>();
        int points = 0;
        int point;
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combs = combinationUtil(arr,data,0,n-1,0, r,combs);

        for (int i = 0; i < combs.size(); i++) {
            List<Integer> comb = combs.get(i);
            List<Card> currentComb = new ArrayList<Card>();
            for (int j = 0; j < comb.size(); j++) {
                currentComb.add(allCards.get(comb.get(j)));
            }
            point = evalComb(currentComb);
            if (point > points) {
                points = point;
                winningComb = currentComb;
            }
        }

        return winningComb;
    }
}
