package utc.pokerut.client.communication;

import utc.pokerut.common.interfaces.client.ComCallsData;

public class Core {

    public DataCallsComImp imp;

    public ComCallsData comCallsData;

    public Client client;

    public Core() {
        this.imp = new DataCallsComImp(this);
        this.client = new Client();
    }

    public void connect(int port, String ip) {
        client.connect(ip, port);

        // start client thread
        Thread thread = new Thread(client);
        thread.start();

        // simple / multiple thread 
        while(true);
    }

    public void setComCallsData(ComCallsData comCallsData) {
        this.comCallsData = comCallsData;
    }
}
