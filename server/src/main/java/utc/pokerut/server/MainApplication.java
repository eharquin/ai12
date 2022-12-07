package utc.pokerut.server;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        utc.pokerut.server.data.Core dataCore = new utc.pokerut.server.data.Core();
        utc.pokerut.server.communication.Core comCore = new utc.pokerut.server.communication.Core();

        comCore.setComCallsData(dataCore.getComCallsData());
        dataCore.setDataCallsCom(comCore.getDataCallsCom());

        comCore.start();

    }

    public static void main(String[] args) {
        launch();
    }
}