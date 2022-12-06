package utc.pokerut.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private utc.pokerut.server.data.Core dataCore;
    private utc.pokerut.server.communication.Core commCore;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        dataCore = new utc.pokerut.server.data.Core();
        commCore = new utc.pokerut.server.communication.Core();


        // INSTANCIATION INTERFACES DATA
        //dataCore.setiDataCallsCom(commCore.getDataCallsCom());

        // INSTANCIATION INTERFAES COMM
        commCore.setComCallsData(dataCore.getComCallsData());
    }

    public static void main(String[] args) {
        launch();
    }
}