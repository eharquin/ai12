package utc.pokerut.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import utc.pokerut.client.communication.commands.*;
// pokerut
import utc.pokerut.common.messages.*;

public class Client extends MessageHandler<Core> implements Runnable {

    private boolean connected;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Core core;

    public Boolean isConnected() {
        return connected;
    }

    public ObjectInputStream getInputStream() {
        return in;
    }

    public ObjectOutputStream getOutputStream() {
        return out;
    }

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

        connected = false;
        this.messages = new HashMap<>();
        this.messages.put(Login.class, CommandUserLoggedIn.class);
        this.messages.put(Logout.class, CommandUserLoggedOut.class);
        this.messages.put(Init.class, CommandInit.class);
        this.messages.put(GameCreated.class, CommandGameCreated.class);
        this.messages.put(GameDeleted.class, CommandGameDeleted.class);
        this.messages.put(JoinGameAsked.class, CommandJoinGameAsked.class);
        this.messages.put(NotifyRejection.class, CommandNotifyRejection.class);
        this.messages.put(PlayerJoinGame.class, CommandPlayerJoinGame.class);

        this.messages.put(UpdateNewRound.class, CommandUpdateNewRound.class);
        this.messages.put(UpdateRoundEnd.class, CommandUpdateRoundEnd.class);
        this.messages.put(UpdateRoundResult.class, CommandUpdateRoundResult.class);
        this.messages.put(ActionRefused.class, CommandActionRefused.class);
        this.messages.put(LaunchGame.class, CommandLaunchGame.class);
    }

    public void connect(String host, int port) {
        if (this.isConnected())
            throw new IllegalArgumentException("client already connected");

        try {
            socket = new Socket(host, port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            connected = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
