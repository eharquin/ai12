package utc.pokerut.client.communication.Commands;

import utc.pokerut.client.communication.Core;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.client.MessageType;

public class CreateGameCommand implements Command {

    public Game game;

    public CreateGameCommand(Game newGame) {
        this.game = game;
    }

    public void execute(Core core) {
        core.getClient().send(MessageType.CreateGame);
        core.getClient().send(this.game);
    }
}
