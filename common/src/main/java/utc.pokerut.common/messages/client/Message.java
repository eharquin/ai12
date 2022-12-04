package utc.pokerut.common.messages.client;

import utc.pokerut.common.messages.client.MessageType;

public class Message {

    public MessageType type;
    
    public Object payLoad;

    public Message(MessageType type, Object payLoad) {
        this.type = type;
        this.payLoad = payLoad;
    }
}
