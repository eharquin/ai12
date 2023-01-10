package utc.pokerut.client.ihmmain.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utc.pokerut.client.ihmmain.ViewNames;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {

    private final BooleanProperty loginView = new SimpleBooleanProperty();
    private final BooleanProperty createProfileView = new SimpleBooleanProperty();
    private final BooleanProperty createGameView = new SimpleBooleanProperty();
    private final BooleanProperty gameListView = new SimpleBooleanProperty();
    private final BooleanProperty inGameView = new SimpleBooleanProperty();
    private final BooleanProperty logoutView = new SimpleBooleanProperty();
    @FXML
    private final BooleanProperty leftPanel = new SimpleBooleanProperty(true);
    @FXML
    private final BooleanProperty chatPartie = new SimpleBooleanProperty();

    public boolean isLoginView() {
        return loginView.get();
    }

    public LeftPanelController getLeftPanelController() {
        return leftPanelController;
    }

    @FXML LeftPanelController leftPanelController;

    public BooleanProperty loginViewProperty() {

        return loginView;
    }

    public GameListController getGameListController() {
        return gameListController;
    }


    @FXML GameListController gameListController;
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

    public boolean isLogoutView() {
        return logoutView.get();
    }

    public BooleanProperty logoutViewProperty() {
        return logoutView;
    }

    public void setLogoutView(boolean logoutView) {
        this.logoutView.set(logoutView);
    }

    public boolean isLeftPanel() {
        return leftPanel.get();
    }

    public BooleanProperty leftPanelProperty() {
        return leftPanel;
    }

    public void setLeftPanel(boolean leftPanel) {
        this.leftPanel.set(leftPanel);
    }

    public boolean isChatPartie() {
        return chatPartie.get();
    }

    public BooleanProperty chatPartieProperty() {
        return chatPartie;
    }

    public void setChatPartie(boolean chatPartie) {
        this.chatPartie.set(chatPartie);
    }

    public void closeViews()
    {
        setLoginView(false);
        setCreateProfileView(false);
        setGameListView(false);
        setCreateGameView(false);
        setIhmGameView(false);
        setLogoutView(false);
    }

    public void closePanelViews()
    {
        setLeftPanel(false);
        setChatPartie(false);
    }
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
            case LOGOUT_VIEW:{
                setLogoutView(true);
                break;
            }
        }

    }

    public void NavigateLeftPannel(ViewNames view)
    {
        closePanelViews();
        switch(view)
        {
            case LEFT_PANEL: {
                setLeftPanel(true);
                break;
            }
            case CHAT_PARTIE: {
                setChatPartie(true);
                break;
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }
}