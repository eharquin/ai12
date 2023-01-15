package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GameEngine {

    private final int STRAIGHT_FLUSH_SEIL = 770503;
    private final int QUADS_SEIL          = 770497;
    private final int FULL_SEIL           = 770330;
    private final int FLUSH_SEIL          = 770163;
    private final int STRAIGHT_SEIL       = 401450;
    private final int SET_SEIL            = 401437;
    private final int TWO_PAIRS_SEIL      = 399256;
    private final int PAIR_SEIL           = 397075;
    private final int HIGH_CARD_SEIL      = 368713;

    /**
     * Get the results of the game, to establish a ranking.
     * @param game
     * @return results
     */
    public ArrayList<Result> getRanking(Game game) {
        ArrayList<Hand> allHands = new ArrayList<>();
        for (Round round: game.getRounds()) {
            allHands.addAll(round.getHands());  // retrieve all the game's Hands
        }
        // sort the list by playerId
        Collections.sort(allHands, Comparator.comparing(ah -> ah.getPlayer().getId()));

        ArrayList<Result> results = new ArrayList<>();
        Result result = new Result();
        Player player = allHands.get(0).getPlayer();
        for (Hand hand : allHands) {
            if (hand.getPlayer().getId() == player.getId()) {
                result.setNbPoints(result.getNbPoints() + hand.getAvailablePoints());
            } else {
                player = hand.getPlayer();
                result = new Result(player, hand.getAvailablePoints());
            }
        }
        // sort by results
        Collections.sort(results, Comparator.comparingInt(Result::getNbPoints).reversed());
        int currentRank = 1;
        int currentPoints = results.get(0).getNbPoints();
        for (Result r : results) {
            if (currentPoints > r.getNbPoints()) {
                currentRank++;
                currentPoints = r.getNbPoints();
            }
            r.setRank(currentRank);
        }
        return results;
    }

    /**
     * Check is the next player can bet.
     * @param round
     * @return boolean
     */
    public boolean isBettingPossible(Round round) {
        return (round.getCurrentBettingRound() != 1 && round.getCurrentBet() == 0);
    }

    /**
     * Check is the next player can call.
     * @param round
     * @return boolean
     */
    public boolean isCallingPossible(Round round) {
        return round.getCurrentBet() != 0;
    }

    /**
     * Check is the next player can raise.
     * @param round
     * @param hand
     * @return boolean
     */
    public boolean isRaisingPossible(Round round, Hand hand) {
        int currentBet = round.getCurrentBet();
        return (currentBet != 0 && hand.getAvailablePoints() > currentBet);
    }

    /**
     * Check is the next player can all in.
     * @param round
     * @param hand
     * @return boolean
     */
    public boolean isAllInPossible(Round round, Hand hand) {
        int currentBet = round.getCurrentBet();
        return (currentBet != 0 && hand.getAvailablePoints() < currentBet);
    }

    /**
     * Check is the next player can check.
     * @param round
     * @return boolean
     */
    public boolean isCheckingPossible(Round round) {
        return (round.getCurrentBettingRound() != 1 && round.getCanCheck());
    }

    /**
     * Check if the next player can fold.
     * @param hand
     * @return
     */
    public boolean isFoldingPossible(Hand hand) {
        return hand.getIsFold();
    }

    /**
     * Returns next player's possible actions.
     * @param round
     * @return possibleActions
     */
    public List<ActionTypeEnum> actionCalculation2(Round round) {
        Hand hand = round.getHandCurrentPlayer();
        List<ActionTypeEnum> possibleActions = new ArrayList<>();
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

    public List<Action> actionCalculation(Round round) {
        Hand hand = round.getHandCurrentPlayer();
        List<Action> possibleActions = new ArrayList<>();
        if (isBettingPossible(round))
            possibleActions.add(new Action(ActionTypeEnum.BET));
        if (isCallingPossible(round))
            possibleActions.add(new Action(ActionTypeEnum.CALL));
        if(isRaisingPossible(round, hand))
            possibleActions.add(new Action(ActionTypeEnum.RAISE));
        if(isCheckingPossible(round))
            possibleActions.add(new Action(ActionTypeEnum.CHECK));
        if(isFoldingPossible(hand))
            possibleActions.add(new Action(ActionTypeEnum.FOLD));
        if(isAllInPossible(round, hand))
            possibleActions.add(new Action(ActionTypeEnum.ALL_IN));
        return possibleActions;
    }

    /**
     * Sort the cards by values.
     * @param cardList
     * @return sortedCards
     */
    public List<Card> sortCards(List<Card> cardList) {
        List<Card> sortedCards = cardList.stream()
                                         .sorted(Comparator.comparing(Card::getValue))
                                         .collect(Collectors.toList());
        return sortedCards;
    }

    /**
     * Check if the combination is a Flush.
     * @param cardList
     * @return boolean
     */
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

    /**
     * Check if the combination is a Straight.
     * @param cardList
     * @return boolean
     */
    public boolean isStraight(List<Card> cardList) {
        boolean flag = true;
        for (int i = 0; i < cardList.size() - 1; i++) {
            flag = flag && (cardList.get(i+1).getValue() == cardList.get(i).getValue()+1);
        }
        return flag;
    }

    /**
     * Check if the combination is a Straight Flush.
     * @param cardList
     * @return boolean
     */
    public boolean isStraightFlush(List<Card> cardList) {
        return isFlush(cardList);
    }

    /**
     * Check if the combination is a Royal Straight Flush.
     * @param cardList
     * @return boolean
     */
    public boolean isRoyalStraightFlush(List<Card> cardList) {
        return cardList.get(0).getValue() == 10;
    }

    /**
     * Get the multiples of a card's value within a set of cards.
     * @param cardList
     * @return multiples
     */
    public HashMap<Integer, Integer> getMultiple(List<Card> cardList) {
        HashMap<Integer, Integer> multiples = new HashMap<>();
        int initValue = cardList.get(0).getValue();
        multiples.put(initValue, 1);
        int ind = 1;

        while (ind <= 5){
            while(cardList.get(ind).getValue() == initValue) {
                if (multiples.containsKey(initValue)) {
                    multiples.replace(initValue, multiples.get(initValue) + 1);
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

    /**
     * Retrieve the values of cards with a given multiplicity "value".
     * @param map
     * @param value
     * @param <Integer>
     * @return
     */
    public static <Integer> List<Integer> getMultipleKeysByValue(HashMap<Integer, Integer> map, Integer value) {
        return map.entrySet()
                  .stream()
                  .filter(entry -> entry.getValue() == value)
                  .map(HashMap.Entry::getKey)
                  .collect(Collectors.toList());
    }

    /**
     * Transform a list of cards' values into a decimal number.
     * @param values
     * @return convertedNumber
     */
    public int convertBase(List<Integer> values) {
        int ind = values.size();
        int convertedNumber = 0;
        while (ind > 0) {
            ind--;
            convertedNumber = convertedNumber * 13 + values.get(ind);
        }
        return convertedNumber;
    }

    /**
     * Evaluate a list of cards' score.
     * @param cardList
     * @return result
     */
    public int evalComb(List<Card> cardList) {

        int result = 0;
        boolean straightCheck = isStraight(cardList);

        if (straightCheck) {
            if(isStraightFlush(cardList)) {
                result = QUADS_SEIL + cardList.get(0).getValue();                                    // straight flush, max = 770 502
                if (isRoyalStraightFlush(cardList)) {
                    result = STRAIGHT_FLUSH_SEIL;
                }
                return result;  // no better combination
            }
            result = SET_SEIL + cardList.get(0).getValue();                                          // straight, max = 401 450
        }

        HashMap<Integer, Integer> multiples = getMultiple(cardList);
        List<Integer> quadruplet = getMultipleKeysByValue(multiples, 4);
        List<Integer> triplet    = getMultipleKeysByValue(multiples, 3);
        List<Integer> doubles    = getMultipleKeysByValue(multiples, 2);
        List<Integer> single     = getMultipleKeysByValue(multiples, 1);

        if(!quadruplet.isEmpty()) {
            result = FULL_SEIL + quadruplet.get(0);                                                  // quads, max = 770 497
        } else {
            boolean set = !triplet.isEmpty();
            if (set && !doubles.isEmpty()) {
                result = FLUSH_SEIL + triplet.get(0);                                                // full, max = 770 330
            } else if (isFlush(cardList)) {
                result = STRAIGHT_SEIL + single.get(0);                                              // flush, max = 770 163
            } else if(set) {
                result = TWO_PAIRS_SEIL + triplet.get(0) + convertBase(single);                      // set, max = 401 437
            } else {
                int nbDouble = doubles.size();
                switch (nbDouble) {
                    case 2 :
                        result = PAIR_SEIL + doubles.get(1) + doubles.get(0) + convertBase(single);  // two pairs, max = 399 256
                    case 1 :
                        result = HIGH_CARD_SEIL + doubles.get(0) + convertBase(single);              // pair, max = 397 075
                    case 0 :
                        result = convertBase(single);                                                // high card, max = 368 713
                }
            }
        }

        return result;
    }

    /**
     * Given an "arr"  input array, "data" temporary array, indexes of "arr" to "star"t and "end", current "index" in "data", size of the combination "r" and a list of "combs",
     * this function return a list of the combinations "combs" of the "arr" elements.
     * @param arr
     * @param data
     * @param start
     * @param end
     * @param index
     * @param r
     * @param combs
     * @return combs
     */
    private List<List<Integer>> combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<List<Integer>> combs) {
        List<Integer> comb = new ArrayList<Integer>();
        if (index == r) {
            for (int j = 0; j < r; j++) {
                comb.add(data[j]);
            }
            combs.add(comb);
            return combs;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r, combs);
        }
        return combs;
    }

    /**
     * Determine the winning combs of r in n cards from "allCards".
     * @param allCards
     * @param r
     * @param n
     * @return winningComb
     */
    private ArrayList<Card> getCombination(ArrayList<Card> allCards, int r, int n) {
        int data[] = new int[r];
        int arr_size = allCards.size();
        int arr[] = new int [arr_size];
        for (int index = 0; index < arr_size; index++){
            arr[index] = index + 1;
        }
        ArrayList<Card> winningComb = new ArrayList<Card>();
        int points = 0;
        int point;
        List<List<Integer>> combs = new ArrayList<List<Integer>>();
        combs = combinationUtil(arr, data, 0, n-1, 0, r, combs);

        for (int i = 0; i < combs.size(); i++) {
            List<Integer> comb = combs.get(i);
            ArrayList<Card> currentComb = new ArrayList<Card>();
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


    /**
     * This function retrieves the players with the higher scores for their winning combination and return a list with all those players.
     * @param hands
     * @param cardsOnTable
     * @return winners
     */
    public ArrayList<Player> getWinners(ArrayList<Hand> hands, ArrayList<Card> cardsOnTable) {

        ArrayList<Player> winners = new ArrayList<>();  // initialisation of the list of winning player(s)
        int win = -1;                                   // comparison value between combination values

        for (Hand h : hands) {
        // scan the hashmap of the combination values of the remaining players at the end of the round
            ArrayList<Card> allCards = new ArrayList<>();
            allCards.addAll(h.getCards());
            allCards.addAll(cardsOnTable);
            ArrayList<Card> cards = getCombination(allCards, 7, 5);
            int valueCombi = evalComb(cards);
            Player player = h.getPlayer();

            if (valueCombi > win) {
            // if the combination vale is higher than the previous one
                winners.clear();        // empty the winner list
                winners.add(player);    // add the new potential winner
                win = valueCombi;       // the comparison variable takes the value of the potential winning combination
            } else if (valueCombi==win) {
            // if the combination vale is the same as the previous one
                winners.add(player);    // add another winner to the list
            }
        }
        return winners;
    }

    /**
     * Returns the list of hands with the availablePoint maj and sorted.
     * @param round
     * @return hands
     */
    public ArrayList<Hand> getResultsRound(Round round) {
        ArrayList<Hand> hands = round.getHands();
        for (Hand h : hands) {
        // scan the hashmap of combination's values of the remaining players at the end of the round
            ArrayList<Card> allCards = new ArrayList<>();
            allCards.addAll(h.getCards());
            allCards.addAll(round.getShowedCards());
            ArrayList<Card> cards = getCombination(allCards, 7, 5);
            h.setValueWinComb(evalComb(cards));
        }
        Collections.sort(hands, Comparator.comparingInt(Hand::getValueWinComb).reversed());
        profitsCalculationRound(hands,round.getPot());
        return hands;
    }

    /**
     * Calculate and maj the profits of the round.
     * @param hands
     * @param pot
     */
    public void profitsCalculationRound (ArrayList<Hand> hands, int pot) {
        ArrayList<Hand> winners = new ArrayList<>();
        int winValue = hands.get(0).getValueWinComb();
        for(Hand h : hands) {
            if(h.getValueWinComb() == winValue)
                winners.add(h);
            else if(h.getValueWinComb()<winValue)
                break;
        }
        int gains = pot/winners.size();

        for (Hand winner: winners) {
            winner.setAvailablePoints(gains);
        }
    }

}
