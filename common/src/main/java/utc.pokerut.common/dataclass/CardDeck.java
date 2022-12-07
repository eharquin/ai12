package utc.pokerut.common.dataclass;

import java.util.*;

public class CardDeck {
    private ArrayList<Card>  cardDeck;

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(ArrayList cardDeck) {
        this.cardDeck = cardDeck;
    }


    public void createCardDeck(){
        // les valeurs vont de 2 à 15
        //les symbols st dans list_symbolq
        List<String> list_symbol = new ArrayList<String>(Arrays.asList("spade", "heart", "clover", "diamond"));

        for (String symbol: list_symbol){
            for (int i = 2; i < 15; i++){
                Card card = new Card(symbol, i);
                this.cardDeck.add(card);
            }
        }
    }

    public void shuffleCardDeck(){
        Collections.shuffle(cardDeck);
    }
}
