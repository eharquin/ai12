package utc.pokerut.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private utc.pokerut.client.ihmmain.Core mainCore;
    private utc.pokerut.client.ihmgame.Core gameCore;

    private utc.pokerut.client.data.Core dataCore;
    private utc.pokerut.client.communication.Core commCore;

    @Override
    public void start(Stage stage) throws IOException {

        mainCore = new utc.pokerut.client.ihmmain.Core(stage);
        //gameCore = new utc.pokerut.client.ihmgame.Core();
        dataCore = new utc.pokerut.client.data.Core();
        commCore = new utc.pokerut.client.communication.Core();

        // INSTANCIATION INTERFACES MAIN
        mainCore.setDataInterface(dataCore.getIhmMainCallsData());
        //mainCore.setComCallsMainInterface(commCore);

        // INSTANCIATION INTERFACES DATA
        dataCore.setiDataCallsIHMMain(mainCore.getDataCallsMainInterface());
        dataCore.setiDataCallsCom(commCore.getDataCallsCom());

        // INSTANCIATION INTERFAES COMM
        commCore.setComCallsData(dataCore.getComCallsData());

        // INSTANCIATION INTERFACES GAME

    }

    @Override
    public void stop() throws Exception {
        mainCore.getDataInterface().logout();
    }

    public static void main(String[] args) {
        launch();
    }
}