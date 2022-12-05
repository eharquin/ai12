package utc.pokerut.server.communication;

import utc.pokerut.common.interfaces.server.ComCallsData;

import java.io.IOException;

public class Core {

    public ComCallsData comCallsData;

    public DataCallsComImp dataCallsComImp;

    private Server server;

    private int port;

    public Core() {
        // port selection (UI)
        this.port = 8889;
        
        try {
            server = new Server(port);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        // start server thread
        try {
            Thread server = new Thread(new Server(port));
            server.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // wait (until threads stop)
        while(true);
    }

    public void setComCallsData(ComCallsData comCallsData) {
        this.comCallsData = comCallsData;
    }
}
