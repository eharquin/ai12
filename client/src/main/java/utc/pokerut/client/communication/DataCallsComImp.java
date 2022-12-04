package utc.pokerut.client.communication;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.DataCallsCom;
import utc.pokerut.common.messages.ClientMessage;
import utc.pokerut.common.messages.client.Message;
import utc.pokerut.common.messages.client.MessageType;

import java.io.IOException;

public class DataCallsComImp implements DataCallsCom
{
    private Core core;

    public DataCallsComImp(Core core)
    {
        this.core = core;
    }

    public ClientProfile AskForProfile(int playerID) {

        return new ClientProfile();
    }

    public void modifyUser(ServerProfile profile) {

    }

    public void initGameClient(Game newGame) {
        Message m = new Message(MessageType.CreateGame, newGame);
        this.core.client.send(m);
    }

    public void sendGame(Game newGame, boolean b) {

    }

    public void askJoinTableMainComCli(int playerID, int idGame) {
        int[] payload = {playerID, idGame};
        Message m = new Message(MessageType.Login, payload);
        this.core.client.send(m);
    }

    public void sendStartGame(int gameID) {

    }

    public void sendAction(int playerID, int gameID, Action action) {

    }

    public void sendMessage(ClientMessage msg) {

    }

    public void receiveMessage(ClientMessage msg) {

    }

    public void leaveGame(int gameID, int playerID) {
        int[] payload = {gameID, playerID};
        Message m = new Message(MessageType.LogOut, payload);
        this.core.client.send(m);
    }

    public void getReplays() {

    }

    public void logoutUser(int playerID) {

    }

}