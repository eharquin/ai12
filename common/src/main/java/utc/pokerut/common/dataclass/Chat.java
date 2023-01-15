package utc.pokerut.common.dataclass;

import java.util.ArrayList;
import java.util.UUID;

public class Chat {

    private UUID idGame;
    private ArrayList<ChatMessage> messages;

    /**
     *
     * @return
     */
    public UUID getIdGame() {
        return idGame;
    }

    /**
     *
     * @param idGame
     */
    public void setIdGame(UUID idGame) {
        this.idGame = idGame;
    }

    /**
     *
     * @return
     */
    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    /**
     *
     * @param messages
     */
    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }

}
