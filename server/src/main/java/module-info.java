module utc.pokerut.server {
    requires javafx.controls;
    requires javafx.fxml;


    opens utc.pokerut.server to javafx.fxml;
    exports utc.pokerut.server;
}