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

public class MainController extends Controller {

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


    public void closeViews()
    {
        setLoginView(false);
        setCreateProfileView(false);
        setGameListView(false);
        setCreateGameView(false);
        setIhmGameView(false);
    }
    private final BooleanProperty createProfileView = new SimpleBooleanProperty();
    private final BooleanProperty createGameView = new SimpleBooleanProperty();
    private final BooleanProperty gameListView = new SimpleBooleanProperty();

    public boolean isIhmGameView() {
        return ihmGameView.get();
    }

    public BooleanProperty ihmGameViewProperty() {
        return ihmGameView;
    }

    public void setIhmGameView(boolean ihmGameView) {
        this.ihmGameView.set(ihmGameView);
    }

    private final BooleanProperty ihmGameView = new SimpleBooleanProperty();




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
            case GAME_LIST_VIEW:{
                setGameListView(true);
                break;
            }
            case IHM_GAME_VIEW:{
                setIhmGameView(true);
                break;
            }
            case CREATE_GAME_VIEW:{
                setCreateGameView(true);
                break;
            }
        }
    }


}