package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.messages.client.MessageType;
import utc.pokerut.server.communication.commands.Command;
import utc.pokerut.server.communication.commands.CommandCreateGame;
import utc.pokerut.server.communication.commands.CommandDeleteGame;
import utc.pokerut.server.communication.commands.CommandLogOut;
import utc.pokerut.server.communication.commands.CommandLogin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ServerProfile profile;

    private Core core; 

    private HashMap<MessageType, Class<? extends Command>> map;

    public void setProfile(ServerProfile profile) {
        this.profile = profile;
    }

    public ServerProfile getProfile() {
        return this.profile;
    }

    public ClientHandler(Core core, Socket socket) throws IOException {
        this.core = core;

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
                MessageType type = (MessageType) in.readObject();
                map.get(type).getDeclaredConstructor().newInstance().execute(core, this);
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

    public void send(Object obj) {
        try {
            this.out.writeObject(obj);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Object receive() {
        try {
            Object obj = in.readObject();
            return obj;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
} 