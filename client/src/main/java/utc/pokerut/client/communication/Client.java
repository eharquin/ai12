package utc.pokerut.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

// pokerut
import utc.pokerut.common.messages.server.Message;
import utc.pokerut.common.messages.server.MessageType;

public class Client implements Runnable{

    private boolean connected;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private HashMap<MessageType, Class<? extends Command>> map;

    public Client() {

        connected = false;
        this.map = new HashMap<>();
        this.map.put(MessageType.UserLogggedIn, CommandUserLoggedIn.class);
        this.map.put(MessageType.UserLoggedOut, CommandUserLoggedIn.class);
        this.map.put(MessageType.Init, CommandUserLoggedIn.class);
        this.map.put(MessageType.GameCreated, CommandUserLoggedIn.class);
        this.map.put(MessageType.GameDeleted, CommandUserLoggedIn.class);
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
                Message message = (Message) in.readObject();
                map.get(message.type).getDeclaredConstructor().newInstance().execute(message.payLoad, out, in);
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

    public void send(utc.pokerut.common.messages.client.Message message) {
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
