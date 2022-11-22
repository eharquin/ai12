package fr.utc.pokerut.common.dataclass;

import java.util.ArrayList;
import java.util.UUID;

public class Chat {
    private UUID idGame;
    private ArrayList<ChatMessage> messages;

    public UUID getIdGame() {
        return idGame;
    }

    public void setIdGame(UUID idGame) {
        this.idGame = idGame;
    }

    public ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<ChatMessage> messages) {
        this.messages = messages;
    }
}
