package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.util.UUID;

public class JoinGameAccepted extends Message {
    public Game game;
    public Player player;
    public UUID playerID;


    public JoinGameAccepted(Game game, Player player, UUID playerID) {
        this.game = game;
        this.player = player;
        this.playerID = playerID;
    }
}
