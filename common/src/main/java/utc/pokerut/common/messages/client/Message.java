package utc.pokerut.common.messages.client;

import java.io.Serializable;

public class Message {

    public MessageType type;
    
    public Serializable payLoad;

    public Message(MessageType type, Serializable payLoad) {
        this.type = type;
        this.payLoad = payLoad;
    }
}
