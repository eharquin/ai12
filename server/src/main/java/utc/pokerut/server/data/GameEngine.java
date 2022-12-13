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
