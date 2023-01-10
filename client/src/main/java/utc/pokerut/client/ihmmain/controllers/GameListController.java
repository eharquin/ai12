package utc.pokerut.client.ihmmain.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
//import utc.pokerut.common.dataclass.Game;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.client.ihmmain.listeners.GameListListener;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import static utc.pokerut.common.dataclass.StatusEnum.*;


public class GameListController extends Controller {
    @FXML
    private TableView<Game> myTableView;

    @FXML
    private TableColumn<Game, String> name;

    @FXML
    private TableColumn<Game, String> creator;

    @FXML
    private TableColumn<Game, String> nbRounds;

    @FXML
    private TableColumn<Game, String> minBet;

    @FXML
    private TableColumn<Game, String> nbPlayers;

    @FXML
    private TableColumn<Game, String> status;

    private Game selectedGame;

    @FXML
    private Label errorMessage;

    public GameListListener getGameListListener() {
        return gameListListener;
    }

    private GameListListener gameListListener;
    public void initialize() {
        gameListListener = new GameListListener(this);
        name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
        creator.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getCreator().getPseudo());
            return property;
        });

        nbRounds.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getNbRounds());
            return property;
        });

        minBet.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getMinimalBet());
            return property;
        });


        nbPlayers.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getPlayers().size() + "/" + Game.getValue().getNbMaxPlayers());
            return property;
        });

        status.setCellValueFactory(Game -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(Game.getValue().getStatus().toString());
            return property;
        });

        //setGameList(getItemsToAdd()); //decommenter pour tester

        myTableView.setRowFactory(tv -> {
            TableRow<Game> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    selectedGame = row.getItem();
                    //selectedId = selectedGame.getId();
                }
            });

            return row;
        });


    }

    private List<Game> getItemsToAdd(){
        List<Game> list = new ArrayList<>();
        ArrayList<Player> playerList1 = new ArrayList<>();
        ArrayList<Player> playerList2 = new ArrayList<>();

        /* ClientProfile client1= new ClientProfile(UUID.randomUUID(),"Bob","avatar","abc", "Bob","Bob",new Date(2000,02,02), "2",3);
        Player player1 =new Player(client1);
        player1.setPseudo("Bob");
        playerList1.add(player1);
        Game game1 = new Game("Partie1",10,200,2,50);

        game1.setStatus(FINISHED);
        game1.setId(UUID.randomUUID());
        game1.setCreator(player1);
        game1.setPlayers(playerList1);

        list.add(game1);*/ //decommenter pour tester

        return list;
    }

    //faire fonction
    public void setGameList(List<Game> gameList){
        // faire différents cas en fonction de si on est dans l'ini ou dans l'actualisation
        myTableView.getItems().setAll(gameList);
    }
    public void addGame(Game game)
    {
        myTableView.getItems().add(game);
    }
    public void removeGame(Game game)
    {
        myTableView.getItems().remove(game);
    }
    public void createGame(){
        //navigate to the screen of game creation
        System.out.println("test");
        core.getMainController().Navigate(ViewNames.CREATE_GAME_VIEW);
    }

    public void generatePopup(ActionEvent event) {
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMinHeight(200);
        progressIndicator.setMinWidth(200);
        Text textChargement = new Text("En attente de joueurs");
        StackPane layout = new StackPane();
        Stage stage = new Stage();
        Scene scene = new Scene(layout, 300, 300);
        stage.initModality(Modality.APPLICATION_MODAL);
        layout.setMargin(progressIndicator, new Insets(50, 50, 50, 50));
        layout.getChildren().addAll(progressIndicator, textChargement);
        stage.setTitle("Chargement");
        stage.setScene(scene);
        core.setWaitingPopupStage(stage);
        core.getWaitingPopupStage().show();
    }

    public void joinGame(ActionEvent event){
        core.getDataInterface().askJoinTableMainComCli(selectedGame.getId(), core.getDataInterface().getProfile().getId());
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Impossible de rejoindre une partie");

        if (selectedGame!=null && selectedGame.getStatus() == WAITING_FOR_PLAYER){
            //currentPlayer=getProfile();
            //cabler avec le lobby
            //aller à la fenêtre d'attente
            generatePopup(event);
            this.selectedGame=null;
        } else if (selectedGame==null){
            alert.setHeaderText("Veuillez selectionner une partie");
            alert.show();
        }else if (selectedGame.getStatus() == FINISHED){
            alert.setHeaderText("La partie est terminée");
            alert.show();
        } else if (selectedGame.getStatus() == ON_GOING) {
            alert.setHeaderText("La partie a déjà commencé");
            alert.show();
        }

    }

}


