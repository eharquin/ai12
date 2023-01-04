package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;

import java.lang.reflect.Array;
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

    public ArrayList<Result> getRanking(Game game) {
        ArrayList<Hand> allHands = new ArrayList<>();
        for(Round round: game.getRounds()){
            allHands.addAll(round.getHands()); // on récupère toutes les mains de la partie
        }
        // on trie la liste de toutes ces mains par playerId
        Collections.sort(allHands, Comparator.comparing(ah -> ah.getPlayer().getId()));

        ArrayList<Result> results = new ArrayList<>();
        Result result = new Result();
        Player player = allHands.get(0).getPlayer();
        for(Hand hand : allHands){
            if(hand.getPlayer().getId() == player.getId()){
                result.setNbPoints(result.getNbPoints()+hand.getAvailablePoints());
            } else {
                player = hand.getPlayer();
                result = new Result(player, hand.getAvailablePoints());
            }
        }

        // on trie par point
        Collections.sort(results, Comparator.comparingInt(Result::getNbPoints).reversed());
        int currentRank = 1;
        int currentPoints = results.get(0).getNbPoints();
        for(Result r : results){
            if(currentPoints > r.getNbPoints()) {
                currentRank++;
                currentPoints = r.getNbPoints();
            }
            r.setRank(currentRank);
        }
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

    /**
     * Fait des combinaisons de 5 cartes à partir d'un paquet de cartes/ si celle de Lidia fonctionne pas
     * @param cardsOnTable
     * @return
     */
    public ArrayList<ArrayList<Card>> getCombinations(ArrayList<Card> cardsOnTable) {
        ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

        for(int i=0; i < cardsOnTable.size()-2; i++) {
            Card card1 = cardsOnTable.get(i);
            for(int j =i+1; j < cardsOnTable.size()-1; j++) {
                Card card2 = cardsOnTable.get(j);
                for(int k = j+1; k < cardsOnTable.size(); k++){
                    Card card3 = cardsOnTable.get(k);
                    for(int l = k+1; l < cardsOnTable.size(); l++){
                        Card card4 = cardsOnTable.get(l);
                        for(int m = l+1; m < cardsOnTable.size(); m++){
                            Card card5= cardsOnTable.get(m);
                            ArrayList<Card> combination = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));
                            combinations.add(combination);
                        }
                    }

                }

            }
        }
        return combinations;
    }
    public ArrayList<Card> getBestCardCombinations(ArrayList<Card> playerCards, ArrayList<Card> cardsOnTable){
        ArrayList<Card> bestCardCombinations = new ArrayList<>();
        // cards : paquet de cartes composé des cartes des joueurs et des cartess sur la table
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(cardsOnTable);
        cards.addAll(playerCards);

        // on récupère la liste des combinaisons possibles avec les 5 cartes
        ArrayList<ArrayList<Card>> combinations = getCombinations(cards);
        int bestCardCombinationsValue = -1;

        for(ArrayList<Card> combination : combinations) {
            int value = evalComb(combination);
            if(value < bestCardCombinationsValue) {
                bestCardCombinationsValue = value;
                bestCardCombinations = combination;
            }
        }

        return bestCardCombinations;
    }

    public ArrayList<Player> getWinners(ArrayList<Hand> hands, ArrayList<Card> cardsOnTable) {

        ArrayList<Player> winners = new ArrayList<>(); //initialisation liste du/des joueur(s) gagnant(s)
        int win = -1; //valeur de comparaison entre valeurs de combinaisons

        for (Hand h : hands) { //parcourir la hashmap des valeurs des combinaisons des joueurs restants à la fin du round
            // ici changer pour adapter au code de Lidia
            ArrayList<Card> cards = getBestCardCombinations(h.getCards(), cardsOnTable);
            int valueCombi = evalComb(cards);
            Player player = h.getPlayer();

            if (valueCombi > win) { //si la valeur de combinaison est supérieure à la précédente
                winners.clear(); //vider la liste des gagnants
                winners.add(player); //ajouter le nouveau potentiel gagnant
                win = valueCombi; //la variable de comparaison win prend la valeur de la bombinaison du potentiel gagnant
            } else if (valueCombi==win) { //si la valeur de combinaison est égale à celle actuelle
                winners.add(player); //ajouter un gagnant supplémentaire à la liste (après le précédent)
            }
        }
        return winners;
    } //à la fin on obtient une liste comprenant le ou les joueurs ayant la valeur de combinaison la plus élevée (donc le ou les gagnants du round)

    /**
     * Fonction moche qui retourne la liste des mains avec le champs availablepoint maj et triée
     * @param round
     * @return
     */
    public ArrayList<Hand> getResultsRound(Round round) {
        ArrayList<Hand> hands = round.getHands();
        for (Hand h : hands) { //parcourir la hashmap des valeurs des combinaisons des joueurs restants à la fin du round
            // ici changer pour adapter au code de Lidia
            ArrayList<Card> cards = getBestCardCombinations(h.getCards(), round.getShowedCards());
            h.setValueWinComb(evalComb(cards));
        }
        Collections.sort(hands, Comparator.comparingInt(Hand::getValueWinComb).reversed());
        profitsCalculationRound(hands,round.getCurrentBets());
        return hands;
    }
    public void profitsCalculationRound (ArrayList<Hand> hands, HashMap<Integer,Integer> currentBets) {
        ArrayList<Hand> winners = new ArrayList<>();
        int winValue = hands.get(0).getValueWinComb();
        for(Hand h : hands) {
            if(h.getValueWinComb() == winValue)
                winners.add(h);
            else if(h.getValueWinComb()<winValue)
                break;
        }
        int gains = 0;
        int totalPot = 0;
        for (int value : currentBets.values()) {
            totalPot += value;
        }
        gains = totalPot/winners.size();

        for (Hand winner: winners) {
            winner.setAvailablePoints(gains);
        }
    }

    public void profitsCalculation (ArrayList<Hand> winners, HashMap<Integer,Integer> currentBets) {
	    int gains = 0;
        int totalPot = 0;
        for (int value : currentBets.values()) {
            totalPot += value;
        }
	    gains = totalPot/winners.size();

	    for (Hand winner: winners) {
            winner.setAvailablePoints(winner.getAvailablePoints()+gains);
        }
	}

//tttt
}
