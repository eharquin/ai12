package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.ClientMessage;

public interface DataCallsCom {

    ClientProfile AskForProfile(int playerID);

    void modifyUser(ServerProfile profile);

    void initGameClient(Game newGame);

    void sendGame(Game newGame, boolean b);

    void askJoinTableMainComCli(int playerID, int idGame);

    void sendStartGame(int gameID);

    void sendAction(int playerID, int gameID, Action action);

    void sendMessage(ClientMessage msg);

    void receiveMessage(ClientMessage msg);

    void leaveGame(int gameID, int playerID);

    void getReplays();

    void logoutUser(int playerID);
    
}
