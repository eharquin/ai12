module utc.pokerut.server {
    requires javafx.controls;
    requires utc.pokerut.common;
    requires ch.qos.logback.classic;
    requires ch.qos.logback.core;
    requires org.slf4j;


    opens utc.pokerut.server to javafx.fxml;
    exports utc.pokerut.server;
}