package utc.pokerut.client.ihmgame;

import javafx.scene.image.Image;
import utc.pokerut.client.MainApplication;
import javafx.fxml.FXML;
import utc.pokerut.common.dataclass.Card;

public class MappingImage {

    //on récupère l'objet image de data
    public Card card;

    public Card getCard() {
        return this.card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    //Méthode de mapping entre le lien url de l'image et l'image stockée dans le code
    public void mapping (Card card) {
        Image playerCard = new Image(String.valueOf(MainApplication.class.getResource("img/ihmgame/"+card.getSymbol()+"/"+card.getValue()+".png")));
    }

}
