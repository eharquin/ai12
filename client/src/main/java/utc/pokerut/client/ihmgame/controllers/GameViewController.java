package utc.pokerut.client.ihmgame.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utc.pokerut.client.MainApplication;
import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.client.ihmgame.pcl.CurrentRoundListener;
import utc.pokerut.client.ihmgame.pcl.PlayersListener;
import utc.pokerut.client.ihmgame.pcl.RoundListener;
import utc.pokerut.client.ihmgame.pcl.StatusListener;
import utc.pokerut.client.ihmmain.controllers.PlayerListController;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ActionTypeEnum;
import utc.pokerut.common.dataclass.Game;

import java.io.IOException;
import java.util.ArrayList;

public class GameViewController {
    public static utc.pokerut.client.ihmgame.Core getCore() {
        return core;
    }

    public static void setCore(utc.pokerut.client.ihmgame.Core _core) {
        core = _core;
    }

    protected static Core core;

    private Game game;

    private ImageView[][] playerCardsImageArray;

    private ImageView[] centerCardsImageArray;

    private Text[] playerNames;

    private ImageView[] playerAvatars;

    private CurrentRoundListener currentRoundListener;
    private PlayersListener playersListener;
    private RoundListener roundListener;
    private StatusListener statusListener;

    public GameViewController(){}

    public void init(Game game){

        this.playersListener = new PlayersListener(this);
        this.currentRoundListener = new CurrentRoundListener(this);
        this.roundListener = new RoundListener(this);
        this.statusListener = new StatusListener(this);

        this.initImageViewPointers();
        this.initGameStatic(game);
    }

    private void initImageViewPointers(){

        this.playerCardsImageArray = new ImageView[3][9];

        card1Player1 = this.playerCardsImageArray[1][1];
        card2Player1 = this.playerCardsImageArray[2][1];
        card1Player2 = this.playerCardsImageArray[1][2];
        card2Player2 = this.playerCardsImageArray[2][2];
        card1Player3 = this.playerCardsImageArray[1][3];
        card2Player3 = this.playerCardsImageArray[2][3];
        card1Player4 = this.playerCardsImageArray[1][4];
        card2Player4 = this.playerCardsImageArray[2][4];
        card1Player5 = this.playerCardsImageArray[1][5];
        card2Player5 = this.playerCardsImageArray[2][5];
        card1Player6 = this.playerCardsImageArray[1][6];
        card2Player6 = this.playerCardsImageArray[2][6];
        card1Player7 = this.playerCardsImageArray[1][7];
        card2Player7 = this.playerCardsImageArray[2][7];
        card1Player8 = this.playerCardsImageArray[1][8];
        card2Player8 = this.playerCardsImageArray[2][8];

        this.centerCardsImageArray = new ImageView[6];

        card1 = this.centerCardsImageArray[1];
        card2 = this.centerCardsImageArray[2];
        card3 = this.centerCardsImageArray[3];
        card4 = this.centerCardsImageArray[4];
        card5 = this.centerCardsImageArray[5];

        this.playerNames = new Text[9];

        Player1 = this.playerNames[1];
        Player2 = this.playerNames[2];
        Player3 = this.playerNames[3];
        Player4 = this.playerNames[4];
        Player5 = this.playerNames[5];
        Player6 = this.playerNames[6];
        Player7 = this.playerNames[7];
        Player8 = this.playerNames[8];

        this.playerAvatars = new ImageView[9];

        avatarPlayer1 = this.playerAvatars[1];
        avatarPlayer2 = this.playerAvatars[2];
        avatarPlayer3 = this.playerAvatars[3];
        avatarPlayer4 = this.playerAvatars[4];
        avatarPlayer5 = this.playerAvatars[5];
        avatarPlayer6 = this.playerAvatars[6];
        avatarPlayer7 = this.playerAvatars[7];
        avatarPlayer8 = this.playerAvatars[8];

    }

    public void initGameStatic(Game game){

        this.game = game;

        nbPlayers = new Text();
        creditGame = new Text();
        miseMin = new Text();

        int nbPlayersInt = game.getNbMaxPlayers();

        nbPlayers.setText(Integer.toString(nbPlayersInt));
        creditGame.setText(Integer.toString(game.getNbPoints()));
        miseMin.setText(Integer.toString(game.getMinimalBet()));

        Image pioche = new Image(String.valueOf(MainApplication.class.getResource("img/ihmgame/other/pioche.png")));

        for (int i=1 ; i <= nbPlayersInt; i++){

            // Initialisation des pseudos
            this.playerNames[i] = new Text(this.game.getPlayers().get(i).getPseudo());

            // Initialisation des cartes des joueurs
            for (int j=1 ; j <= 2 ; j++){
                this.playerCardsImageArray[i][j].setImage(pioche);
            }
        }

        for (int i=1 ; i < 6 ; i++){
            this.centerCardsImageArray[i].setImage(pioche);
        }

        // this.setNbPlayers(new Text((Integer.toString(game.getNbMaxPlayers()))));
        // nbPlayers.toString();
        //Text test = new Text(Integer.toString(game.getNbPoints()));
        //this.setCreditGame(test);
        //this.setMiseMin(new Text(Integer.toString(game.getMinimalBet())));
        //System.out.println(this.creditGame) ;
    }

    @FXML
    public TextField betting;
    @FXML
    public TextField raising;

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

    @FXML
    private Text creditPlayer1;

    @FXML
    private Text creditPlayer2;

    @FXML
    private Text creditPlayer3;

    @FXML
    private Text creditPlayer4;

    @FXML
    private Text creditPlayer5;

    @FXML
    private Text creditPlayer6;

    @FXML
    private Text creditPlayer7;

    @FXML
    private Text creditPlayer8;

    @FXML
    private Text Player1;

    @FXML
    private Text Player2;

    @FXML
    private Text Player3;

    @FXML
    private Text Player4;

    @FXML
    private Text Player5;

    @FXML
    private Text Player6;

    @FXML
    private Text Player7;

    @FXML
    private Text Player8;

    @FXML
    private ImageView card1Player1;

    @FXML
    private ImageView card2Player1;

    @FXML
    private ImageView card1Player2;

    @FXML
    private ImageView card2Player2;

    @FXML
    private ImageView card1Player3;

    @FXML
    private ImageView card2Player3;

    @FXML
    private ImageView card1Player4;

    @FXML
    private ImageView card2Player4;

    @FXML
    private ImageView card1Player5;

    @FXML
    private ImageView card2Player5;

    @FXML
    private ImageView card1Player6;

    @FXML
    private ImageView card2Player6;

    @FXML
    private ImageView card1Player7;

    @FXML
    private ImageView card2Player7;

    @FXML
    private ImageView card1Player8;

    @FXML
    private ImageView card2Player8;

    @FXML
    private ImageView avatarPlayer1;

    @FXML
    private ImageView avatarPlayer2;

    @FXML
    private ImageView avatarPlayer3;

    @FXML
    private ImageView avatarPlayer4;

    @FXML
    private ImageView avatarPlayer5;

    @FXML
    private ImageView avatarPlayer6;

    @FXML
    private ImageView avatarPlayer7;

    @FXML
    private ImageView avatarPlayer8;

    public Text getCreditPlayer7() {
        return creditPlayer7;
    }

    public void setCreditPlayer7(Text creditPlayer7) {
        this.creditPlayer7 = creditPlayer7;
    }

    public Text getCreditPlayer8() {
        return creditPlayer8;
    }

    public void setCreditPlayer8(Text creditPlayer8) {
        this.creditPlayer8 = creditPlayer8;
    }

    public Text getPlayer7() {
        return Player7;
    }

    public void setPlayer7(Text player7) {
        Player7 = player7;
    }

    public Text getPlayer8() {
        return Player8;
    }

    public void setPlayer8(Text player8) {
        Player8 = player8;
    }

    public ImageView getCard1Player1() {
        return card1Player1;
    }

    public void setCard1Player1(Image imagecard1Player1) {
        this.card1Player1.setImage(imagecard1Player1);
    }

    public ImageView getCard2Player1() {
        return card2Player1;
    }

    public void setCard2Player1(Image imagecard2Player1) {
        this.card2Player1.setImage(imagecard2Player1);
    }

    public ImageView getCard1Player2() {
        return card1Player2;
    }

    public void setCard1Player2(Image imagecard1Player2) {
        this.card1Player2.setImage(imagecard1Player2);
    }

    public ImageView getCard2Player2() {
        return card2Player2;
    }

    public void setCard2Player2(Image imagecard2Player2) {
        this.card2Player2.setImage(imagecard2Player2);
    }

    public ImageView getCard1Player3() {
        return card1Player3;
    }

    public void setCard1Player3(Image imagecard1Player3) {
        this.card1Player3.setImage(imagecard1Player3);
    }

    public ImageView getCard2Player3() {
        return card2Player3;
    }

    public void setCard2Player3(Image imagecard2Player3) {
        this.card2Player3.setImage(imagecard2Player3);
    }

    public ImageView getCard1Player4() {
        return card1Player4;
    }

    public void setCard1Player4(Image imagecard1Player4) {
        this.card1Player4.setImage(imagecard1Player4);
    }

    public ImageView getCard2Player4() {
        return card2Player4;
    }

    public void setCard2Player4(Image imagecard2Player4) {
        this.card2Player4.setImage(imagecard2Player4);
    }

    public ImageView getCard1Player5() {
        return card1Player5;
    }

    public void setCard1Player5(Image imagecard1Player5) {
        this.card1Player5.setImage(imagecard1Player5);
    }

    public ImageView getCard2Player5() {
        return card2Player5;
    }

    public void setCard2Player5(Image imagecard2Player5) {
        this.card2Player5.setImage(imagecard2Player5);
    }

    public ImageView getCard1Player6() {
        return card1Player6;
    }

    public void setCard1Player6(Image imagecard1Player6) {
        this.card1Player6.setImage(imagecard1Player6);
    }


    public ImageView getCard2Player6() {
        return card2Player6;
    }

    public void setCard2Player6(Image imagecard2Player6) {
        this.card2Player6.setImage(imagecard2Player6);
    }

    public ImageView getCard1Player7() {
        return card1Player7;
    }

    public void setCard1Player7(Image imagecard1Player7) {
        this.card1Player7.setImage(imagecard1Player7);
    }

    public ImageView getCard2Player7() {
        return card2Player7;
    }

    public void setCard2Player7(Image imagecard2Player7) {
        this.card2Player7.setImage(imagecard2Player7);
    }

    public ImageView getCard1Player8() {
        return card1Player8;
    }

    public void setCard1Player8(Image imagecard1Player8) {
        this.card1Player8.setImage(imagecard1Player8);
    }

    public ImageView getCard2Player8() {
        return card2Player8;
    }

    public void setCard2Player8(Image imagecard2Player8) {
        this.card2Player8.setImage(imagecard2Player8);
    }

    public ImageView getAvatarPlayer1() {
        return avatarPlayer1;
    }

    public void setAvatarPlayer1(ImageView avatarPlayer1) {
        this.avatarPlayer1 = avatarPlayer1;
    }

    public ImageView getAvatarPlayer2() {
        return avatarPlayer2;
    }

    public void setAvatarPlayer2(ImageView avatarPlayer2) {
        this.avatarPlayer2 = avatarPlayer2;
    }

    public ImageView getAvatarPlayer3() {
        return avatarPlayer3;
    }

    public void setAvatarPlayer3(ImageView avatarPlayer3) {
        this.avatarPlayer3 = avatarPlayer3;
    }

    public ImageView getAvatarPlayer4() {
        return avatarPlayer4;
    }

    public void setAvatarPlayer4(ImageView avatarPlayer4) {
        this.avatarPlayer4 = avatarPlayer4;
    }

    public ImageView getAvatarPlayer5() {
        return avatarPlayer5;
    }

    public void setAvatarPlayer5(ImageView avatarPlayer5) {
        this.avatarPlayer5 = avatarPlayer5;
    }

    public ImageView getAvatarPlayer6() {
        return avatarPlayer6;
    }

    public void setAvatarPlayer6(ImageView avatarPlayer6) {
        this.avatarPlayer6 = avatarPlayer6;
    }

    public ImageView getAvatarPlayer7() {
        return avatarPlayer7;
    }

    public void setAvatarPlayer7(ImageView avatarPlayer7) {
        this.avatarPlayer7 = avatarPlayer7;
    }

    public ImageView getAvatarPlayer8() {
        return avatarPlayer8;
    }

    public void setAvatarPlayer8(ImageView avatarPlayer8) {
        this.avatarPlayer8 = avatarPlayer8;
    }

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

    public void setCard1(Image cardimage1) {
        this.card1.setImage(cardimage1);
    }

    public ImageView getCard2() {
        return card2;
    }

    public void setCard2(Image cardimage2) {
        this.card2.setImage(cardimage2);
    }

    public ImageView getCard3() {
        return card3;
    }

    public void setCard3(Image cardimage3) {
        this.card3.setImage(cardimage3);
    }

    public ImageView getCard4() {
        return card4;
    }

    public void setCard4(Image cardimage4) {
        this.card4.setImage(cardimage4);
    }

    public ImageView getCard5() {
        return card5;
    }

    public void setCard5(Image cardimage5) {
        this.card5.setImage(cardimage5);
    }

    public ImageView getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(Image cardimagedeck) {
        this.cardDeck.setImage(cardimagedeck);
    }

    public Text getCreditPlayer1() {
        return creditPlayer1;
    }

    public void setCreditPlayer1(Text creditPlayer1) {
        this.creditPlayer1 = creditPlayer1;
    }

    public Text getCreditPlayer2() {
        return creditPlayer2;
    }

    public void setCreditPlayer2(Text creditPlayer2) {
        this.creditPlayer2 = creditPlayer2;
    }

    public Text getCreditPlayer3() {
        return creditPlayer3;
    }

    public void setCreditPlayer3(Text creditPlayer3) {
        this.creditPlayer3 = creditPlayer3;
    }

    public Text getCreditPlayer4() {
        return creditPlayer4;
    }

    public void setCreditPlayer4(Text creditPlayer4) {
        this.creditPlayer4 = creditPlayer4;
    }

    public Text getCreditPlayer5() {
        return creditPlayer5;
    }

    public void setCreditPlayer5(Text creditPlayer5) {
        this.creditPlayer5 = creditPlayer5;
    }

    public Text getCreditPlayer6() {
        return creditPlayer6;
    }

    public void setCreditPlayer6(Text creditPlayer6) {
        this.creditPlayer6 = creditPlayer6;
    }

    public Text getPlayer1() {
        return Player1;
    }

    public void setPlayer1(Text player1) {
        Player1 = player1;
    }

    public Text getPlayer2() {
        return Player2;
    }

    public void setPlayer2(Text player2) {
        Player2 = player2;
    }

    public Text getPlayer3() {
        return Player3;
    }

    public void setPlayer3(Text player3) {
        Player3 = player3;
    }

    public Text getPlayer4() {
        return Player4;
    }

    public void setPlayer4(Text player4) {
        Player4 = player4;
    }

    public Text getPlayer5() {
        return Player5;
    }

    public void setPlayer5(Text player5) {
        Player5 = player5;
    }

    public Text getPlayer6() {
        return Player6;
    }

    public void setPlayer6(Text player6) {
        Player6 = player6;
    }

    public Game getGame() {
        return game;
    }

    public Text[] getPlayerNames() { return this.playerNames; }

    public void setPlayerNames(Text playername, int x) { playerNames[x] = playername; }

    public ImageView[][] getPlayerCardsImageArray() { return playerCardsImageArray; }

    public void setPlayerCardsImageArray(ImageView imageView, int x, int y) { playerCardsImageArray[x][y] = imageView; }

    public ImageView[] getCenterCardsImageArray() { return centerCardsImageArray; }

    public void setCenterCardsImageArray(ImageView cardImage, int x) { centerCardsImageArray[x] = cardImage; }



    /*@FXML
    public void Leave(ActionEvent event) throws IndexOutOfBoundsException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/confirmLeaveGame.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();}*/

    @FXML
    public void Raise(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/RaiseAction.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();

    }
    @FXML
    public void RaiseValue(javafx.event.ActionEvent event){
        event.consume();
        System.out.println(raising.getText());
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        // Call Data : montant de la relance retiré de ses crédits et ajouté au pot

        // Calling round to get player
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.RAISE);
        action.setBetting(Integer.parseInt(raising.getText()));
        // action.setTimestamp(new Timestamp());

        // sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }
    @FXML
    public void Call(javafx.event.ActionEvent event){

        // Call Data : montant de la mise retiré de ses crédits et ajouté au pot
        // Si pas dernier joueur et pas eu de relance -> fin du tour ? Est-ce à nous de gérer ça?
        // go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.CALL);
        // Récupérer la mise du round en cours Int mise = ...
        // action.setBetting(mise);
        // action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);

    }
    @FXML
    public void Check(javafx.event.ActionEvent event){
        // Inform Data, go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.CHECK);
        // Récupérer la mise du round en cours Int mise = ...
        action.setBetting(0);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }
    @FXML
    public void Fold(javafx.event.ActionEvent event){
        // Inform Data, go to next player
        event.consume();
        Action action = new Action();
        action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        action.setType(ActionTypeEnum.FOLD);
        // Récupérer la mise du round en cours Int mise = ...
        action.setBetting(0);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);
    }

    @FXML
    public void Bet(javafx.event.ActionEvent event ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/BetAction.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public void BetValue(ActionEvent event){
        event.consume();
        System.out.println(betting.getText());
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        // Calling Data with the value
        Action action = new Action();
        // Action.setPlayer(PlayerListController.getCore().getPlayerConnected());
        // action.setType(ActionTypeEnum.); // Demander implémentation de BET à DATA
        // Récupérer la mise du round en cours Int mise = ...
        // action.setBetting(mise);
        //action.setTimestamp(new Timestamp());

        //sendAction(PlayerListController.getCore().getPlayerConnected().getId(), getCore().getGame().getId(), action);

    }

    public void updatePlayers(Game game){
        // load every player in the game
        // if player leave, update view
    }


}
