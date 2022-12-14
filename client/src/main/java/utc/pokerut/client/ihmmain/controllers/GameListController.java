package utc.pokerut.client.ihmmain.controllers;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
//import utc.pokerut.common.dataclass.Game;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import static utc.pokerut.common.dataclass.StatusEnum.ON_GOING;
import static utc.pokerut.common.dataclass.StatusEnum.WAITING_FOR_PLAYER;


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

    }

    private List<Game> getItemsToAdd(){
        List<Game> list = new ArrayList<>();
        /*ArrayList<Player> playerList1 = new ArrayList<>();
        ArrayList<Player> playerList2 = new ArrayList<>();

        ClientProfile client1= new ClientProfile();
        Player player1 =new Player(client1);
        player1.setPseudo("Bob");
        playerList1.add(player1);
        Game game1 = new Game();

        game1.setName("Partie1");
        game1.setCreator(player1);
        game1.setNbMaxPlayers(10);
        game1.setMinimalBet(2);
        game1.setPlayers(playerList1);
        game1.setNbRounds(2);
        game1.setStatus(WAITING_FOR_PLAYER);

        list.add(game1);

        System.out.println(game1.getName());
        System.out.println(player1.getPseudo());

        Player player2 =new Player(client1);
        player2.setPseudo("Eve");
        playerList2.add(player2);
        Player player3 =new Player(client1);
        player3.setPseudo("Oscar");
        playerList2.add(player3);
        Game game2 = new Game();

        game2.setName("Partie2");
        game2.setCreator(player3);
        game2.setNbMaxPlayers(5);
        game2.setMinimalBet(10);
        game2.setPlayers(playerList2);
        game2.setNbRounds(50);
        game2.setStatus(ON_GOING);

        list.add(game2);*/

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
        core.getMainController().Navigate(ViewNames.CREATE_GAME_VIEW);
    }

    public void joinGame(){
        System.out.println(selectedGame);

        if (selectedGame!=null){

            //currentPlayer=getProfile();
            //core.getDataInterface().addUserToGameDataMainClient(selectedGame, serverProfile, idUser );
            //aller à la fenêtre d'attente
            this.selectedGame=null;
        } else {
            errorMessage.setVisible(true);
            /*JOptionPane.showMessageDialog(null,
                    "Aucune partie sélectionnée",
                        "PopUp Dialog",
                    JOptionPane.ERROR_MESSAGE);*/
            }


    }

    public void refreshGameList(){
        //récuperer la nouvelle valeur de la liste dans le listener
        //rappeler displayGameTables
    }

    
}