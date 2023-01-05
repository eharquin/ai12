package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;

public class LaunchGame extends Message {

    public Game game;
    public LaunchGame(Game game) {
        this.game = game;
    }
}
