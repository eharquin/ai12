package utc.pokerut.server.communication;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class Server implements Runnable
{
    private ServerSocket socket;
    private boolean started;

    private List<ClientHandler> clients;

    public boolean isStarted() {
        return started;        
    } 

    Server(int port) throws IOException {
        socket = new ServerSocket(port);
        started = false;
        clients = new ArrayList<ClientHandler>();
    }

    public void run() {
        started = true;
        System.out.print("Server started");
        while(true) {
            System.out.println(" .... listen");
            listen();
        }
    }

    private void listen() {
        try {
            Socket clientSocket = socket.accept();
            System.out.println("Connection acceptedd");

            ClientHandler client = new ClientHandler(clientSocket, clients.size());
            clients.add(client);

            Thread thread = new Thread(client);
            thread.start();
            System.out.println("thread started");

        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}