package utc.pokerut.server.communication;

import utc.pokerut.common.messages.server.Message;
import utc.pokerut.common.messages.server.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    private HashMap<MessageType, Class<? extends Command>> map;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;

        outStream = new ObjectOutputStream(socket.getOutputStream());
        inStream = new ObjectInputStream(socket.getInputStream());

        this.map = new HashMap<>();
        this.map.put(MessageType.UserLoggedOut, CommandLogin.class);
        this.map.put(MessageType.UserLoggedOut, CommandLogOut.class);
        this.map.put(MessageType.GameCreated, CommandCreateGame.class);
        this.map.put(MessageType.GameDeleted, CommandDeleteGame.class);
    }

    public void run() {
        while (true) {
            try {
                Message message = (Message) inStream.readObject();
                map.get(message.type).getDeclaredConstructor().newInstance().execute(message.payLoad);
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
} 