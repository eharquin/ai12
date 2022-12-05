package utc.pokerut.common.messages.server;

import utc.pokerut.common.messages.server.MessageType;

import java.io.Serializable;

public class Message implements Serializable {

    public MessageType type;
    
    public Serializable payLoad;

    public Message(MessageType type, Serializable payLoad) {
        this.type = type;
        this.payLoad = payLoad;
    }
}
