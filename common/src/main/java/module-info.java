module utc.pokerut.common {
    requires java.desktop;
    requires java.sql;

    exports utc.pokerut.common.dataclass;
    exports utc.pokerut.common.interfaces.client;
    exports utc.pokerut.common.interfaces.server;
    exports utc.pokerut.common.messages.client;
    exports utc.pokerut.common.messages.server;
}