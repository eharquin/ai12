package utc.pokerut.server.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.messages.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server implements Runnable
{
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private ServerSocket socket;
    private boolean started;

    private List<ClientHandler> clients;

    private Core core;

    public boolean isStarted() {
        return started;        
    } 

    public List<ClientHandler> getClients() {
        return clients;
    }

    public ClientHandler getClientById(UUID id){
        for (ClientHandler client : clients) {
            if (client.getProfile().getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public void broadcast(Message message) {
        for (ClientHandler client : clients) {
            client.send(message);
        }
    }

    public void broadcastExcept(Message message, UUID exept) {
        for (ClientHandler client : clients) {
            if (!client.getProfile().getId().equals(exept)) {
                client.send(message);
            }
        }
    }

    public void broadcast(Message message, List<UUID> players) {
        for (UUID player : players) {
            getClientById(player).send(message);
        }
    }

    public void broadcastPlayers(Message message, List<Player> players) {
        for (Player player : players) {
            getClientById(player.getId()).send(message);
        }
    }

    Server(Core core, int port) throws IOException {
        this.core = core;
        socket = new ServerSocket(port);
        started = false;
        clients = new ArrayList<ClientHandler>();
    }

    public void run() {
        started = true;
        logger.info("Listening on port " + socket.getLocalPort());
        while(true) {
            listen();
        }
    }

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    private void listen() {
        try {
            Socket clientSocket = socket.accept();
            logger.info("Connection accepted from " + clientSocket.getInetAddress());

            ClientHandler client = new ClientHandler(this, core, clientSocket);

            Thread thread = new Thread(client);
            thread.start();
            logger.info("Thread started for client " + clientSocket.getInetAddress());

        } catch(IOException e) {
            logger.error("Error while accepting connection", e);
        }
    }
}