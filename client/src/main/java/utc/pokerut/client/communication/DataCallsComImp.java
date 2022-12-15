package utc.pokerut.client.communication;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.DataCallsCom;
import utc.pokerut.common.messages.GameCreated;
import utc.pokerut.common.messages.LoginMessage;
import utc.pokerut.common.messages.LogoutMessage;
import utc.pokerut.common.messages.client.MessageType;

import java.util.UUID;

public class DataCallsComImp implements DataCallsCom
{
    private Core core;

    public DataCallsComImp(Core core)
    {
        this.core = core;
    }

    public void connectionUser(ServerProfile profile, String ip, int port) {
        core.connect(ip, port);
        core.getClient().send(new LoginMessage(profile));
    }

    public ClientProfile AskForProfile(int playerID) {

        return null;
    }

    public void modifyUser(ServerProfile profile) {

    }

    public void initGameClient(Game newGame) {
        core.getClient().send(new GameCreated(newGame));
    }

    public void sendGame(Game newGame, boolean b) {

    }

    public void askJoinTableMainComCli(UUID playerID, UUID idGame) {
//        core.getClient().send(MessageType.Login);
//        core.getClient().send(playerID);
//        core.getClient().send(idGame);
    }

    public void sendStartGame(int gameID) {

    }

    public void sendAction(int playerID, int gameID, Action action) {

    }

    public void sendMessage(MessageType msg) {

    }

    public void receiveMessage(utc.pokerut.common.messages.server.MessageType msg) {

    }

    public void leaveGame(int gameID, int playerID) {
        
    }

    public void getReplays() {

    }

    public void logoutUser(UUID playerID) {
        core.getClient().send(new LogoutMessage(playerID));
    }

}