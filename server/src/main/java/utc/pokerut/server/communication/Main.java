package utc.pokerut.server.communication;

public class Main {

    public static void main(String[] args) {

        Core core = new Core();
        
        // Pass ComCallsData implementation to core
        //core.setComCallsData();

        core.start();
    }
    
}
