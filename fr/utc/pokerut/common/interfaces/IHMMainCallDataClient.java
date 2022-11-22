package fr.utc.pokerut.common.interfaces;


import fr.utc.pokerut.common.dataclass.Game;
import fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.UUID;

public interface IHMMainCallDataClient {
    public void displayGame(Game newGame);
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser);
}
