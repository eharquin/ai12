package src.fr.utc.pokerut.common.dataclass;

import java.security.Timestamp;

public class ChatMessage {
    private Player sender;
    private String content;
    private Timestamp timestamp;

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
