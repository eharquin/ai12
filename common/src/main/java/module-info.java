module utc.pokerut.common {
    requires java.desktop;
    requires org.slf4j;

    exports utc.pokerut.common.dataclass;
    exports utc.pokerut.common.interfaces.client;
    exports utc.pokerut.common.interfaces.server;
    exports utc.pokerut.common.messages;
}