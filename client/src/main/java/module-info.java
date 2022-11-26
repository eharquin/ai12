module utc.pokerut.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires utc.pokerut.common;


    opens utc.pokerut.client to javafx.fxml;
    exports utc.pokerut.client;
}