package utc.pokerut.client.communication;

import utc.pokerut.client.communication.commands.*;
import utc.pokerut.common.messages.*;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class Client extends MessageHandler<Core> implements Runnable {

    private Core core;

    @Override
    public Core getCore() {
        return core;
    }

    @Override
    protected Command getCommand(Class<? extends Command> commandClass) {
        try {
            return commandClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Client(Core core) {
        this.core = core;
        this.messages = new HashMap<>();
        this.messages.put(Login.class, CommandUserLoggedIn.class);
        this.messages.put(Logout.class, CommandUserLoggedOut.class);
        this.messages.put(Init.class, CommandInit.class);
        this.messages.put(GameCreated.class, CommandGameCreated.class);
        this.messages.put(GameDeleted.class, CommandGameDeleted.class);
        this.messages.put(JoinGameAsked.class, CommandJoinGameAsked.class);
        this.messages.put(NotifyRejection.class, CommandNotifyRejection.class);
        this.messages.put(NotifyAcceptance.class, CommandNotifyAcceptance.class);
        this.messages.put(PlayerJoinGame.class, CommandPlayerJoinGame.class);
        this.messages.put(JoinGameAccepted.class, CommandJoinGameAccepted.class);

        this.messages.put(UpdateNewRound.class, CommandUpdateNewRound.class);
        this.messages.put(UpdateRoundEnd.class, CommandUpdateRoundEnd.class);
        this.messages.put(ActionRefused.class, CommandActionRefused.class);
        this.messages.put(LaunchGame.class, CommandLaunchGame.class);
    }

    public void connect(String host, int port) {
        if (this.isConnected())
            throw new IllegalArgumentException("client already connected");

        try {
            this.setSocket(new Socket(host, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
