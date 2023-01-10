package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.*;
import utc.pokerut.server.communication.commands.*;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler extends MessageHandler<Core> implements Runnable {

    private Server server;

    private ServerProfile profile;

    private Core core;

    public void setProfile(ServerProfile profile) {
        this.profile = profile;
    }

    public ServerProfile getProfile() {
        return this.profile;
    }

    public ClientHandler(Server server, Core core, Socket socket) throws IOException {
        this.core = core;
        this.server = server;
        this.setSocket(socket);
        this.server.addClient(this);

        this.messages = new HashMap<>();
        this.messages.put(Login.class, CommandLogin.class);
        this.messages.put(Logout.class, CommandLogOut.class);
        this.messages.put(GameCreated.class, CommandCreateGame.class);
        this.messages.put(GameDeleted.class, CommandDeleteGame.class);
//        this.messages.put(MessageType.AskJoinGame, CommandLogin.class);
        this.messages.put(ActionPlayed.class, CommandActionPlayed.class);
        this.messages.put(AskJoinGame.class, CommandAskJoinGame.class);
        this.messages.put(NotifyRejection.class, CommandNotifyRejection.class);
        this.messages.put(NotifyAcceptance.class, CommandNotifyAcceptance.class);
        this.messages.put(SubmitAction.class, CommandSubmitAction.class);
    }

    @Override
    protected Core getCore() {
        return core;
    }

    @Override
    protected utc.pokerut.common.messages.Command getCommand(Class<? extends utc.pokerut.common.messages.Command> commandClass) {
        try {
            ServerCommand command = (ServerCommand) commandClass.getConstructor().newInstance();
            command.setClient(this);
            return command;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void disconnect() {
        super.disconnect();
        this.server.removeClient(this);
    }
}