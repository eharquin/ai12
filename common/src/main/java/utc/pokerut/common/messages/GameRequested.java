package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;

import java.util.UUID;

public class GameRequested extends Message{
    public Game game;
    public UUID player;

    public GameRequested(Game game, UUID player) {
        this.game = game;
        this.player = player;
    }
}
