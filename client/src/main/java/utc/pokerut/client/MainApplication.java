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
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        MainController controller = fxmlLoader.getController();
        core = new Core(controller);
        controller.setCore(core);
        controller.setLoginView(true);
        stage.setTitle("Poker UT - Texas Holdem");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}