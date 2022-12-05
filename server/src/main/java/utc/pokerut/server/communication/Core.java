package utc.pokerut.server.communication;

import utc.pokerut.common.interfaces.server.ComCallsData;

import java.io.IOException;

public class Core {
    
    private ComCallsData comCallsData;
    
    private DataCallsComImp dataCallsComImp;
    
    private Server server;
    
    private int port;
    
    public ComCallsData getComCallsData() {
        return this.comCallsData;
    }

    public void setComCallsData(ComCallsData comCallsData) {
        this.comCallsData = comCallsData;
    }

    public Server getServer() {
        return server;
    }

    public Core() {
        // port selection (UI)
        this.port = 8889;
        
        try {
            server = new Server(this, port);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        Thread serverThread = new Thread(server);
        serverThread.start();

        // wait (until threads stop)
        while(true);
    }

}
