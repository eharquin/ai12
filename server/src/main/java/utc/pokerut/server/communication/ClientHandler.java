package utc.pokerut.server.communication;

import utc.pokerut.common.messages.client.Message;
import utc.pokerut.common.messages.client.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private HashMap<MessageType, Class<? extends Command>> map;

    public ClientHandler(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        this.map = new HashMap<>();
        this.map.put(MessageType.Login, CommandLogin.class);
        this.map.put(MessageType.LogOut, CommandLogOut.class);
        this.map.put(MessageType.CreateGame, CommandCreateGame.class);
        this.map.put(MessageType.DeleteGame, CommandDeleteGame.class);
    }

    public void run() {
        while (true) {
            try {
                Message message = (Message) in.readObject();
                map.get(message.type).getDeclaredConstructor().newInstance().execute(message.payLoad, out , in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void send(utc.pokerut.common.messages.server.Message message) {
        try {
            this.out.writeObject(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
} 