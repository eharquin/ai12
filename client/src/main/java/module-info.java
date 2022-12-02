module utc.pokerut.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires utc.pokerut.common;
    requires java.desktop;
    requires java.sql;


    opens utc.pokerut.client to javafx.fxml;
    exports utc.pokerut.client;
    exports utc.pokerut.client.ihmmain.controllers;
    opens utc.pokerut.client.ihmmain.controllers to javafx.fxml;
}