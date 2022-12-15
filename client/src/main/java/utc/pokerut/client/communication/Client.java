package utc.pokerut.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

import utc.pokerut.client.communication.Commands.*;
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
        this.messages.put(LoginMessage.class, CommandUserLoggedIn.class);
        this.messages.put(LogoutMessage.class, CommandUserLoggedOut.class);
        this.messages.put(InitMessage.class, CommandInit.class);
        this.messages.put(GameCreated.class, CommandGameCreated.class);
        this.messages.put(GameDeleted.class, CommandGameDeleted.class);
        this.messages.put(ActionPlayed.class, CommandActionPlayed.class);
        this.messages.put(ActionRefused.class, CommandActionRefused.class);
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
