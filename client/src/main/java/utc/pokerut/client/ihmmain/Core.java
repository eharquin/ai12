package utc.pokerut.client.ihmmain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc.pokerut.client.MainApplication;
import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.client.ihmgame.PlayGameController;
import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.client.ihmmain.implementations.ComCallsIHMMainImpl;
import utc.pokerut.client.ihmmain.implementations.DataCallsIHMMainImpl;
import utc.pokerut.client.ihmmain.implementations.IHMGameCallsIHMMainImpl;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.*;

import java.io.IOException;

public class Core {
    private IHMMainCallsIHMGame gameInterface;
    private IHMMainCallsData dataInterface;
    private IHMGameCallsIHMMain gameCallsMainInterface;

    public ComCallsIHMMain getComCallsMainInterface() {
        return comCallsMainInterface;
    }

    public void setComCallsMainInterface(ComCallsIHMMain comCallsMainInterface) {
        this.comCallsMainInterface = comCallsMainInterface;
    }

    public DataCallsIHMMain getDataCallsMainInterface() {
        return dataCallsMainInterface;
    }

    public void setDataCallsMainInterface(DataCallsIHMMain dataCallsMainInterface) {
        this.dataCallsMainInterface = dataCallsMainInterface;
    }

    private ComCallsIHMMain comCallsMainInterface;
    private DataCallsIHMMain dataCallsMainInterface;

    public IHMMainCallsIHMGame getGameInterface() {
        return gameInterface;
    }

    public void setGameInterface(IHMMainCallsIHMGame gameInterface) {
        this.gameInterface = gameInterface;
    }

    public IHMMainCallsData getDataInterface() {
        return dataInterface;
    }

    public void setDataInterface(IHMMainCallsData dataInterface) {
        this.dataInterface = dataInterface;
    }

    public IHMGameCallsIHMMain getGameCallsMainInterface() {
        return gameCallsMainInterface;
    }

    public void setGameCallsMainInterface(IHMGameCallsIHMMain gameCallsMainInterface) {
        this.gameCallsMainInterface = gameCallsMainInterface;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Player getPlayerConnected() {
        return playerConnected;
    }

    public void setPlayerConnected(Player playerConnected) {
        this.playerConnected = playerConnected;
    }

    private MainController mainController;

    private PlayGameController playGameController;

    public Scene getScene() {
        return scene;
    }

    private Scene scene;
    private Stage stage;
    private Player playerConnected;
    public Core(Stage stage) throws IOException
    {
       // FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/mainWindow.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/gameTablePlay.fxml"));

        scene = new Scene(fxmlLoader.load());
        playGameController = fxmlLoader.getController();
       // mainController = fxmlLoader.getController();
       // mainController.setCore(this);
       // mainController.setLoginView(true);
        this.stage = stage;
        this.stage.setTitle("Poker UT - Texas Holdem");
        this.stage.setResizable(false);
        this.stage.setScene(scene);
        this.stage.show();

        setGameCallsMainInterface(new IHMGameCallsIHMMainImpl(this));
        setDataCallsMainInterface(new DataCallsIHMMainImpl(this));
        setComCallsMainInterface(new ComCallsIHMMainImpl(this));
    }
}

