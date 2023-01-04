package utc.pokerut.common.messages;

import java.util.UUID;

public class AskJoinGame extends Message {
    public UUID game;
    public UUID player;

    public AskJoinGame(UUID game, UUID player) {
        this.game = game;
        this.player = player;
    }
}
