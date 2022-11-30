package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.client.ihmmain.listeners.PlayerListListener;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Player;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {

    private final BooleanProperty loginView = new SimpleBooleanProperty();

    public boolean isLoginView() {
        return loginView.get();
    }

    public BooleanProperty loginViewProperty() {
        return loginView;
    }

    public void setLoginView(boolean loginView) {
        this.loginView.set(loginView);
    }

    public boolean isCreateProfileView() {
        return createProfileView.get();
    }

    public BooleanProperty createProfileViewProperty() {
        return createProfileView;
    }

    public void setCreateProfileView(boolean createProfileView) {
        this.createProfileView.set(createProfileView);
    }

    public boolean isCreateGameView() {
        return createGameView.get();
    }

    public BooleanProperty createGameViewProperty() {
        return createGameView;
    }

    public void setCreateGameView(boolean createGameView) {
        this.createGameView.set(createGameView);
    }

    public boolean isGameListView() {
        return gameListView.get();
    }

    public BooleanProperty gameListViewProperty() {
        return gameListView;
    }

    public void setGameListView(boolean gameListView) {
        this.gameListView.set(gameListView);
    }

    public PlayerListListener getPlayerListListener() {
        return playerListListener;
    }

    private PlayerListListener playerListListener;
    public void closeViews()
    {
        setLoginView(false);
        setCreateProfileView(false);
        setGameListView(false);
        setCreateGameView(false);
    }
    private final BooleanProperty createProfileView = new SimpleBooleanProperty();
    private final BooleanProperty createGameView = new SimpleBooleanProperty();
    private final BooleanProperty gameListView = new SimpleBooleanProperty();



    @FXML
    private Button profileButton;

    @FXML
    private Button viewGameButton;

    @FXML
    private Button logoutButton;
    @FXML
    private ListView list;
    private ObservableList<String> observableList = FXCollections.observableArrayList();

    public void Navigate(ViewNames view)
    {
        closeViews();
        switch(view)
        {
            case LOGIN_VIEW: {
                setLoginView(true);
                break;
            }
            case CREATE_PROFILE_VIEW: {
                setCreateProfileView(true);
                break;
            }
        }
    }
    @FXML
    protected void profileButtonClicked() {
        Navigate(ViewNames.CREATE_PROFILE_VIEW);
    }
    @FXML
    protected void viewGameButtonClicked() {
        Navigate(ViewNames.LOGIN_VIEW);
    }
    @FXML
    protected void logoutButtonClicked() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.getItems().setAll("User 1", "User 2", "User 3", "User 4");
        playerListListener = new PlayerListListener(this);
    }

    public void setPlayerList(List<Player> playerList) {
        list.getItems().clear();
        for(Player player : playerList){
            list.getItems().add(player.getPseudo());
        }
    }
}