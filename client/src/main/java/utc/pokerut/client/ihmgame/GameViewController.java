package utc.pokerut.client.ihmgame;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.common.dataclass.Game;

public class GameViewController {
    public static utc.pokerut.client.ihmgame.Core getCore() {
        return core;
    }

    public static void setCore(utc.pokerut.client.ihmgame.Core _core) {
        core = _core;
    }

    protected static Core core;

    public void initGame(Game game){
        nbPlayers.setText(Integer.toString(game.getNbMaxPlayers()));
        creditGame.setText(Integer.toString(game.getNbPoints()));
        miseMin.setText(Integer.toString(game.getMinimalBet()));
       // this.setNbPlayers(new Text((Integer.toString(game.getNbMaxPlayers()))));
        // nbPlayers.toString();
        //Text test = new Text(Integer.toString(game.getNbPoints()));
        //this.setCreditGame(test);
        //this.setMiseMin(new Text(Integer.toString(game.getMinimalBet())));
        //System.out.println(this.creditGame) ;
    }



    //Number of players in the game
    @FXML
    private Text nbPlayers;

    //Credit of the entire game
    @FXML
    private Text creditGame;

    //Minimum mise to play at the table
    @FXML
    private Text miseMin;

    //Credit during one round on the table
    @FXML
    private Text creditTable;

    //Leave the game
    @FXML
    private Button leaveGame;

    //First card on the table
    @FXML
    private ImageView card1;

    //Second card on the table
    @FXML
    private ImageView card2;

    //Third card on the table
    @FXML
    private ImageView card3;

    //Fourth card on the table
    @FXML
    private ImageView card4;

    //Fifth card on the table
    @FXML
    private ImageView card5;

    //Deck card
    @FXML
    private ImageView cardDeck;

    public Text getNbPlayers() {
        return nbPlayers;
    }

    public void setNbPlayers(Text nbPlayers) {
        this.nbPlayers = nbPlayers;
    }

    public Text getCreditGame() {
        return creditGame;
    }

    public void setCreditGame(Text creditGame) {
        this.creditGame = creditGame;
    }

    public Text getMiseMin() {
        return miseMin;
    }

    public void setMiseMin(Text miseMin) {
        this.miseMin = miseMin;
    }

    public Text getCreditTable() {
        return creditTable;
    }

    public void setCreditTable(Text creditTable) {
        this.creditTable = creditTable;
    }

    public Button getLeaveGame() {
        return leaveGame;
    }

    public void setLeaveGame(Button leaveGame) {
        this.leaveGame = leaveGame;
    }

    public ImageView getCard1() {
        return card1;
    }

    public void setCard1(ImageView card1) {
        this.card1 = card1;
    }

    public ImageView getCard2() {
        return card2;
    }

    public void setCard2(ImageView card2) {
        this.card2 = card2;
    }

    public ImageView getCard3() {
        return card3;
    }

    public void setCard3(ImageView card3) {
        this.card3 = card3;
    }

    public ImageView getCard4() {
        return card4;
    }

    public void setCard4(ImageView card4) {
        this.card4 = card4;
    }

    public ImageView getCard5() {
        return card5;
    }

    public void setCard5(ImageView card5) {
        this.card5 = card5;
    }

    public ImageView getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(ImageView cardDeck) {
        this.cardDeck = cardDeck;
    }
}
