package utc.pokerut.client.ihmmain.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.client.ihmmain.listeners.PlayerListListener;
import utc.pokerut.common.dataclass.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerListController extends Controller implements Initializable {
    @FXML
    private Button profileButton;

    @FXML
    private Button viewGameButton;

    @FXML
    private Button logoutButton;
    @FXML
    private ListView list;
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    public PlayerListListener getPlayerListListener() {
        return playerListListener;
    }

    private PlayerListListener playerListListener;
    public void setPlayerList(List<Player> playerList) {
        list.getItems().clear();
        for(Player player : playerList){
            list.getItems().add(player.getPseudo());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.getItems().setAll("User 1", "User 2", "User 3", "User 4");
        playerListListener = new PlayerListListener(this);
    }
    @FXML
    protected void profileButtonClicked() {
    }
    @FXML
    protected void viewGameButtonClicked() {
        core.getMainController().Navigate(ViewNames.VIEW_GAME);
    }
    @FXML
    protected void logoutButtonClicked() {
        core.getMainController().Navigate(ViewNames.LOGOUT_VIEW);
    }
}
