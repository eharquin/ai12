package utc.pokerut.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.client.ihmmain.controllers.MainController;

import java.io.IOException;

public class MainApplication extends Application {
    private Core core;
    @Override
    public void start(Stage stage) throws IOException {

        core = new Core(stage);


    }

    public static void main(String[] args) {
        launch();
    }
}