package utc.pokerut.client.communication;

public class Core {

    public DataCallsComImp imp;

    public Client client;

    public Core()
    {
        this.imp = new DataCallsComImp(this);
        this.client = new Client();
    }
}
