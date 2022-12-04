package utc.pokerut.server.communication;

import utc.pokerut.common.messages.server.Message;
import utc.pokerut.common.messages.server.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ClientHandler implements Runnable {

    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    // test
    private int n;

    public ClientHandler(Socket socket, int n) throws IOException {
        this.socket = socket;

        outStream = new ObjectOutputStream(socket.getOutputStream());
        inStream = new ObjectInputStream(socket.getInputStream());

        // test
        this.n = n;
    }

    public void run() {
        // test
        try {
            System.out.println("sending message");
            outStream.writeObject(new Message(MessageType.UserLogggedIn, "test"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
        }
    }
} 