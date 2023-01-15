package utc.pokerut.common.dataclass;

import java.security.Timestamp;

public class ChatMessage {

    private Player sender;
    private String content;
    private Timestamp timestamp;

    /**
     *
     * @return
     */
    public Player getSender() {
        return sender;
    }

    /**
     *
     * @param sender
     */
    public void setSender(Player sender) {
        this.sender = sender;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
