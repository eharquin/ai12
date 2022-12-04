package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.Card;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Result;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Result> getRanking() {
        ArrayList<Result> results = new ArrayList<>();
        return results;
    }

    public boolean isBettingPossible() {
        return true;
    }

    public boolean isAllInPossible(){
        return true;
    }

    public boolean isCheckingPossible(){
        return true;
    }

    public boolean isRaisingPossible(){
        return true;
    }

    public boolean isCallingPossible(){
        return true;
    }

    public List<Card> sortCards(List<Card> cardList){
        List<Card> cards = null;
        // ordre croissant des valeurs, peu importe les symboles
        return cards;
    }

    public boolean isQuinte(List<Card> cardList){
        //vérifie qui +1 entre chaque card.value
        return true;
    }

    public boolean isQuinteFlush(List<Card> cardList){
        //vérifie que même symbol sur ttes les cards
        return true;
    }

    public boolean isQuinteFlushRoyale(List<Card> cardList){
        //vérifie que max = as/min = 10
        return true;
    }
    public void getMultiple(List<Card> cardList){
        // hmap pour les multiples
        /* pour une combinaison, tant qu'il y a des cartes à parcourir*/
        int nbCards = 5;
        int initValue = 0 ; // mettre value première carte

        while (nbCards > 0){ // nbCards-=1 après parcours
            /* while carte suivante a la même value que initValue
                   ajouter 1 à la multiplicité de la carte
             */
        }
        // retourne le ou les multiples avec la valeur associée
    }

    public boolean isSameColor(List<Card> cardList){
        //toutes les cartes ont le même attribut symbol
        return true;
    }

    public int translateResults(){ // type de l'argument ?
        int points = 0;
        return points;
    }
}
