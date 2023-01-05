package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.util.UUID;

import java.util.UUID;

public interface DataCallsIHMMain {
    public void displayGame(Game game);

    public void addUserToGameDataMainCli(Game gameNewPlayer, Player newPlayer, UUID idUser);
}
