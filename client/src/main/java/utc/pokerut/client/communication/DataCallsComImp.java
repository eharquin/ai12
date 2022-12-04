package utc.pokerut.client.communication;

import utc.pokerut.common.interfaces.client.DataCallsCom;

public class DataCallsComImp implements DataCallsCom
{
    private Core core;

    public DataCallsComImp(Core core)
    {
        this.core = core;
    }

    ClientProfile AskForProfile(int playerID) {

        // we can not instantiate this class
        return new ClientProfile();
    }

    void modifyUser(ServerProfile profile) {

    }

    void initGameClient(Game newGame) {

    }

    void sendGame(Game newGame, boolean b) {

    }

    void askJoinTableMainComCli(int playerID, int idGame) {

    }

    void sendStartGame(int gameID) {

    }

    void sendAction(int playerID, int gameID, Action action) {

    }

    void sendMessage(ClientMessage msg) {

    }

    void receiveMessage(ClientMessage msg) {

    }

    void leaveGame(int gameID, int playerID) {

    }

    void getReplays() {

    }

    void logoutUser(int playerID) {

    }

}