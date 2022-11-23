module utc.pokerut.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens utc.pokerut.client to javafx.fxml;
    exports utc.pokerut.client;
}