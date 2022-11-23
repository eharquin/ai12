package src.fr.utc.pokerut.common.interfaces.client;


import src.fr.utc.pokerut.common.dataclass.Game;
import src.fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.Date;
import java.util.UUID;

public interface IHMMainCallsData {
    public void displayGame(Game newGame);
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser);
    public void login(String login, String password);
    public void createUser(UUID id, String pseudo, String name, String surname, Date birthdate, String IP, String Port);
}
