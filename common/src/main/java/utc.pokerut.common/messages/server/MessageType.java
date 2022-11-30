package utc.pokerut.common.messages.server;

import java.io.Serializable;

public enum MessageType implements Serializable {
    UserLogggedIn,
    UserLoggedOut,
    Init, // transporte la liste des partie, des utilisateurs et autres infos
    GameCreated, // notify all player that a new game has been created
    GameDeleted, 
}
