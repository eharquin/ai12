package utc.pokerut.server.communication;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private void listen() {
        try {
            Socket clientSocket = socket.accept();
            logger.info("Connection accepted from " + clientSocket.getInetAddress());

            ClientHandler client = new ClientHandler(core, clientSocket);
            clients.add(client);

            Thread thread = new Thread(client);
            thread.start();
            logger.info("Thread started for client " + clientSocket.getInetAddress());

        } catch(IOException e) {
            logger.error("Error while accepting connection", e);
        }
    }
}