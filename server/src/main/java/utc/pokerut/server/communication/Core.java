package utc.pokerut.server.communication;

import utc.pokerut.common.interfaces.server.ComCallsData;

import java.io.IOException;

public class Core {

    public ComCallsData comCallsData;

    public static void main(String[] args) {

        // interface graphique
        int port = Integer.parseInt(args[0]);

        try {
            Thread server = new Thread(new Server(port));
            server.start();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        while(true);
    }
}
