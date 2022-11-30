package client.src.main.java.utc.pokerut.client.data;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;

import java.util.Date;
import java.util.UUID;

public class IHMMainCallsDataClientImpl implements IHMMainCallsData {
    @Override
    public void displayGame(Game newGame) {

    }

    @Override
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser) {

    }

    @Override
    public void login(String login, String password){

    }

    @Override
    public void createUser(UUID id, String pseudo, String name, String surname, Date birthdate, String IP, String Port){

    }
}
