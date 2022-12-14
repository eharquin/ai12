package utc.pokerut.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

import utc.pokerut.client.communication.Commands.*;
// pokerut
import utc.pokerut.common.messages.server.MessageType;

public class Client implements Runnable{

    private boolean connected;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Core core;

    private HashMap<MessageType, Class<? extends Command>> map;

    public boolean isConnected() {
        return connected;
    }

    public ObjectInputStream getInputStream() {
        return in;
    }

    public ObjectOutputStream getOutputStream() {
        return out;
    }

    public Client(Core core) {
        this.core = core;

        connected = false;
        this.map = new HashMap<>();
        this.map.put(MessageType.UserLoggedIn, CommandUserLoggedIn.class);
        this.map.put(MessageType.UserLoggedOut, CommandUserLoggedOut.class);
        this.map.put(MessageType.Init, CommandInit.class);
        this.map.put(MessageType.GameCreated, CommandGameCreated.class);
        this.map.put(MessageType.GameDeleted, CommandGameDeleted.class);
        this.map.put(MessageType.ActionPlayed, CommandActionPlayed.class);
        this.map.put(MessageType.ActionRefused, CommandActionRefused.class);
    }

    public void connect(String host, int port) {
        if(connected)
            throw new IllegalArgumentException("client already connected");

        try {
            socket = new Socket(host, port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            connected = true;
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(connected)
        {
            try {
                MessageType type = (MessageType) in.readObject();
                map.get(type).getDeclaredConstructor().newInstance().execute(core);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void send(Object obj) {
        try {
            this.out.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object receive() {
        try {
            Object obj = this.in.readObject();
            return obj;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
