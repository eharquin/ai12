package utc.pokerut.client.communication;

import utc.pokerut.common.interfaces.client.ComCallsData;
import utc.pokerut.common.interfaces.client.DataCallsCom;

public class Core {

    private DataCallsComImp dataCallsComImp;

    private ComCallsData comCallsData;

    private Client client;

    public Client getClient() {
        return client;
    }

    public ComCallsData getComCallsData() {
        return comCallsData;
    }

    public DataCallsCom getDataCallsCom() {
        return dataCallsComImp;
    }
    
    public void setComCallsData(ComCallsData comCallsData) {
        this.comCallsData = comCallsData;
    }

    public Core() {
        this.dataCallsComImp = new DataCallsComImp(this);
        this.client = new Client(this);
    }

    public void connect(int port, String ip) {
        client.connect(ip, port);

        // start client thread
        Thread thread = new Thread(client);
        thread.start();

        // simple / multiple thread 
        //while(true);
    }
}
