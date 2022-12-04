package utc.pokerut.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.client.ihmmain.controllers.MainController;

import java.io.IOException;

public class MainApplication extends Application {
    private utc.pokerut.client.ihmmain.Core mainCore;
    private utc.pokerut.client.ihmgame.Core gameCore;

    private utc.pokerut.client.data.Core dataCore;
    private utc.pokerut.client.communication.Core commCore;

    @Override
    public void start(Stage stage) throws IOException {

        mainCore = new utc.pokerut.client.ihmmain.Core(stage);
        gameCore = new utc.pokerut.client.ihmgame.Core();
        dataCore = new utc.pokerut.client.data.Core();
        commCore = new utc.pokerut.client.communication.Core();

        // INSTANCIATION INTERFACES MAIN
        mainCore.setDataInterface(dataCore.getIhmMainCallsData());
        //mainCore.setComCallsMainInterface(commCore);

        // INSTANCIATION INTERFACES DATA
        dataCore.setiDataCallsIHMMain(mainCore.getDataCallsMainInterface());
        //dataCore.setiDataCallsCom(commCore.);
        // INSTANCIATION INTERFAES COMM

        // INSTANCIATION INTERFACES GAME

    }

    public static void main(String[] args) {
        launch();
    }
}