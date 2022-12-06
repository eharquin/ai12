module utc.pokerut.common {
    requires java.desktop;

    exports utc.pokerut.common.dataclass;
    exports utc.pokerut.common.interfaces.client;
    exports utc.pokerut.common.interfaces.server;
    exports utc.pokerut.common.messages;
    exports utc.pokerut.common.messages.client;
    exports utc.pokerut.common.messages.server;
}