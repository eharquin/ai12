package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.UUID;

public interface DataCallsCom {

    public void connectionUser(ServerProfile profile, String ip, int port);

    ClientProfile AskForProfile(UUID playerID);

    void modifyUser(ServerProfile profile);

    void initGameClient(Game newGame);

    void sendGame(Game newGame, boolean b);

    void askJoinTableMainComCli(UUID playerID, UUID idGame);

    void sendStartGame(UUID gameID);

    void sendAction(UUID playerID, UUID gameID, Action action);

    void leaveGame(UUID gameID, UUID playerID);

    void getReplays();

    void logoutUser(UUID playerID);


}
