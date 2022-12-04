package utc.pokerut.client.communication;

public class Core {
    public static void main(String[] args) {
        System.out.println("null");
        Client client = new Client("localhost", 8898);
        client.connect();
        client.run();
    }
}
