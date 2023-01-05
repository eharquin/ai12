package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;

import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.UUID;

public interface IHMMainCallsData {
    public void setPCLGame(PropertyChangeListener PCLGame);
    public void setPCLPlayer(PropertyChangeListener PCLPlayer);
    public void displayGame(Game newGame);
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser);
    public void login(String login, String password, String ip, int port) throws Exception;
    public void logout() throws Exception;
    public void createUser(String pseudo, String password, String name, String surname, Date birthdate, String avatar, String ip, int port) throws Exception;

    // call from Main to transmit to Com
    public void askJoinTableMainComCli(UUID idGame, UUID idUser);
    public ClientProfile getProfile();
}
