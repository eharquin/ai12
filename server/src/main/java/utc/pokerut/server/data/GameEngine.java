package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;

import java.util.*;
import java.util.stream.Collectors;

import static utc.pokerut.server.data.GameEngine.CombinationEnum.*;

public class GameEngine {

    public enum CombinationEnum {
        QUINT_FLUSH, QUINT_FLUSH_ROYAL, QUINT,CARRE,FULL,COULEUR,BRELAN,DOUBLE_PAIR,CARTE_HAUTE,PAIR
    }

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

    public CombinationEnum evalComb(List<Card> cardList){

        CombinationEnum comb = null;
        boolean quinteCheck = isQuinte(cardList);

        if (quinteCheck){
            if(isQuinteFlush(cardList)){
                comb = QUINT_FLUSH;
                if (isQuinteFlushRoyale(cardList)){
                    comb = QUINT_FLUSH_ROYAL;
                }
                return comb; // no better combination
            }
            comb = QUINT;
        }

        HashMap<Integer, Integer> multiples = getMultiple(cardList);

        if(multiples.containsValue(4)){
            comb = CARRE;
        } else {
            boolean brelan = multiples.containsValue(3);
            if (brelan && multiples.containsValue(2)) {
                    comb = FULL;
            } else if (isSameColor(cardList)) {
                comb = COULEUR;
            } else if(brelan) {
                comb = BRELAN;
            } else {
                List<Integer> doubles = getMultipleKeysByValue(multiples, 2);
                int nbDouble = doubles.size();
                switch (nbDouble){
                    case 2 :
                        comb = DOUBLE_PAIR;
                    case 1 :
                        comb = PAIR;
                    case 0 :
                        comb = CARTE_HAUTE;
                }
            }
        }

        return comb;
    }

    public int translateResults(CombinationEnum combinationName){
        int points = 0;
        return points;
    }

    public ArrayList<ArrayList<Card>> getCombinations(ArrayList<Card> cardsOnTable) {
        ArrayList<ArrayList<Card>> combinations = new ArrayList<>();

        for(int i=0; i < cardsOnTable.size()-2; i++) {
            Card card1 = cardsOnTable.get(i);
            for(int j =i+1; j < cardsOnTable.size()-1; j++) {
                Card card2 = cardsOnTable.get(j);
                for(int k = j+1; k < cardsOnTable.size(); k++){
                    Card card3 = cardsOnTable.get(k);
                    ArrayList<Card> combination = new ArrayList<>(Arrays.asList(card1, card2, card3));
                    combinations.add(combination);
                }

            }
        }
        return combinations;
    }
    public ArrayList<Card> getBestCardCombinations(ArrayList<Card> playerCards, ArrayList<Card> cardsOnTable){
        ArrayList<Card> bestCardCombinations = new ArrayList<>();
        ArrayList<ArrayList<Card>> combinations = getCombinations(cardsOnTable);
        int bestCardCombinationsValue = -1;

        for(ArrayList<Card> combination : combinations) {
            ArrayList<Card> combinationToEval = new ArrayList<>();
            combinationToEval.addAll(combination);
            combinationToEval.addAll(playerCards);
            int value = translateResults(evalComb(combinationToEval));
            if(value < bestCardCombinationsValue) {
                bestCardCombinationsValue = value;
                bestCardCombinations = combinationToEval;
            }
        }

        return bestCardCombinations;
    }

    public ArrayList<Player> getWinners(ArrayList<Hand> hands, ArrayList<Card> cardsOnTable) {

        ArrayList<Player> winners = new ArrayList<>(); //initialisation liste du/des joueur(s) gagnant(s)
        int win = -1; //valeur de comparaison entre valeurs de combinaisons
        int nbWinners = 0; //nombre de gagnants

        for (Hand h : hands) { //parcourir la hashmap des valeurs des combinaisons des joueurs restants à la fin du round
            ArrayList<Card> cards = getBestCardCombinations(h.getCards(), cardsOnTable);
            int valueCombi = translateResults(evalComb(cards));
            Player player = h.getPlayer();

            if (valueCombi > win) { //si la valeur de combinaison est supérieure à la précédente
                winners.clear(); //vider la liste des gagnants
                winners.add(player); //ajouter le nouveau potentiel gagnant
                win = valueCombi; //la variable de comparaison win prend la valeur de la bombinaison du potentiel gagnant
                nbWinners = 1; // On a un gagnant

            } else if (valueCombi==win) { //si la valeur de combinaison est égale à celle actuelle
                winners.add(player); //ajouter un gagnant supplémentaire à la liste (après le précédent)
                nbWinners++;//on augmente le nombre de gagnant de +1
            }
        }
        return winners;
    } //à la fin on obtient une liste comprenant le ou les joueurs ayant la valeur de combinaison la plus élevée (donc le ou les gagnants du round)

    /*
    public ArrayList<int> isWinner (HashMap<Integer, Integer> map) {

        List<int> winners = new ArrayList<int>(Arrays.asList(0)); //initialisation liste du/des joueur(s) gagnant(s)
        int win = -1; //valeur de comparaison entre valeurs de combinaisons
        int nb_winner = 0; //nombre de gagnants

        for (HashMap.Entry<int,int> entry : combinations.entrySet()) { //parcourir la hashmap des valeurs des combinaisons des joueurs restants à la fin du round
            int value_combi = entry.getValue();
            int player = entry.getKey();

            if (value_combi>win) { //si la valeur de combinaison est supérieure à la précédente
                winners.clear(); //vider la liste des gagnants
                winners.add(0,player); //ajouter le nouveau potentiel gagnant
                int win = value_combi; //la variable de comparaison win prend la valeur de la bombinaison du potentiel gagnant
                int nb_winner=1; } //on augmente le nombre de gagnants à 1

            else if (value_combi==win) { //si la valeur de combinaison est égale à la supérieure
                winners.add(nb_winner,player); //ajouter un gagnant supplémentaire à la liste (après le précédent)
                int nb_winner=nb_winner+1; } //on augmente le nombre de gagnant de +1
        }
    } //à la fin on obtient une liste comprenant le ou les joueurs ayant la valeur de combinaison la plus élevée (donc le ou les gagnants du round)

    public void profitsCalculation (List winners, Hashmap CurrentBets) {
	    int gains = 0;
        totalPot = somme valeurs Hashmap CurrentBets;
	    gains = totalPot/winners.size();
	    for (int winner: winners) {
	    	winner.Hand.availablePoints += gains }
	}

     */
}



