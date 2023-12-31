package utc.pokerut.client.communication;

import utc.pokerut.client.communication.Commands.CreateGameCommand;
import utc.pokerut.client.communication.Commands.LoginCommand;
import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.DataCallsCom;
import utc.pokerut.common.messages.client.MessageType;

public class DataCallsComImp implements DataCallsCom
{
    private Core core;

    public DataCallsComImp(Core core)
    {
        this.core = core;
    }

    public void connectionUser(ServerProfile profile, String ip, int port) {



        core.connect(ip, port);

        // send new LoginCommand
        LoginCommand command = new LoginCommand(profile);
        command.execute(core);
    }

    public ClientProfile AskForProfile(int playerID) {

        return null;
    }

    public void modifyUser(ServerProfile profile) {

    }

    public void initGameClient(Game newGame) {
        CreateGameCommand command = new CreateGameCommand(newGame);
        command.execute(core);
    }

    public void sendGame(Game newGame, boolean b) {

    }

    public void askJoinTableMainComCli(int playerID, int idGame) {
        
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

    public void logoutUser(int playerID) {

    }

}