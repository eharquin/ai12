package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;

public class GameCreated extends Message {
    public Game game;

    public GameCreated(Game game) {
        this.game = game;
    }
}
