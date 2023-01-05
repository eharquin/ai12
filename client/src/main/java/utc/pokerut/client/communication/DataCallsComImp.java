package utc.pokerut.client.communication;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.DataCallsCom;
import utc.pokerut.common.messages.*;

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
        core.getClient().send(new Login(profile));
    }

    public ClientProfile AskForProfile(UUID playerID) {

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
        core.getClient().send(new AskJoinGame(idGame, playerID));
    }

    public void sendStartGame(UUID gameID) {

    }

    public void sendAction(UUID playerID, UUID gameID, Action action) {
        core.getClient().send(new SubmitAction(playerID, gameID, action));
    }

    public void leaveGame(UUID gameID, UUID playerID) {
        
    }

    public void getReplays() {

    }

    public void logoutUser(UUID playerID) {
        core.getClient().send(new Logout(playerID));
    }

}