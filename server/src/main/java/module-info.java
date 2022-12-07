module utc.pokerut.server {
    requires javafx.controls;
    requires utc.pokerut.common;


    opens utc.pokerut.server to javafx.fxml;
    exports utc.pokerut.server;
}