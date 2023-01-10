package utc.pokerut.client.ihmgame;

import utc.pokerut.common.messages.Message;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Listener implements Runnable{

    public static String username;
    private static ObjectOutputStream oos;

    @Override
    public void run() {

    }

    public static void send(String msg) throws IOException {
        oos.writeObject(msg);
        oos.flush();
    }
}
