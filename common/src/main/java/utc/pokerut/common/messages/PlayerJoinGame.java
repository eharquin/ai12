package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.UUID;

public class PlayerJoinGame extends Message {

    public UUID playerID;
    public Game game;
    public ServerProfile profile;

    public PlayerJoinGame(Game game, ServerProfile profile, UUID playerID) {
        this.game = game;
        this.playerID = playerID;
        this.profile = profile;
    }
}
