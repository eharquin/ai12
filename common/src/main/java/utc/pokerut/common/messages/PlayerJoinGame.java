package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.util.UUID;

public class PlayerJoinGame extends Message {

    public UUID playerID;
    public Game game;
    public Player player;

    public PlayerJoinGame(Game game, Player player, UUID playerID) {
        this.game = game;
        this.playerID = playerID;
        this.player = player;
    }
}
