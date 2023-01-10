package utc.pokerut.common.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

abstract public class MessageHandler<T> {

    private Socket socket;

    private ObjectInputStream in;

    private ObjectOutputStream out;

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public boolean isConnected() {
        return this.getSocket() != null && !this.getSocket().isClosed() && this.getSocket().isConnected();
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        try {
            this.out = new ObjectOutputStream(this.getSocket().getOutputStream());
            this.in = new ObjectInputStream(this.getSocket().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectInputStream getInputStream() {
        return in;
    }

    public ObjectOutputStream getOutputStream() {
        return out;
    }

    abstract protected T getCore();

    abstract protected Command getCommand(Class<? extends Command> commandClass);

    protected HashMap<Class<? extends Message>, Class<? extends Command>> messages;

    public void run() {
        while (this.isConnected()) {
            Message message = this.receive();
            if (message != null) {
                logger.info("Message received : " + message.getClass());
                Command command = this.getCommand(messages.get(message.getClass()));
                command.setCore(this.getCore());
                command.setMessage(message);
                logger.debug("Running command : " + command.getClass());
                command.execute();
                logger.debug("Message handled : " + message.getClass());
            }
        }
        this.disconnect();
    }

    public Message receive() {
        Message message = null;
        try {
            message = (Message) this.getInputStream().readObject();
        } catch (EOFException e) {
            this.disconnect();
            return null;
        } catch (Exception e) {
            logger.error("Error while reading message", e);
        }
        return message;
    }

    public void send(Message message) {
        if (!this.isConnected()) throw new RuntimeException("Client is not connected");
        System.out.println(this.isConnected());
        try {
            logger.info("Sending message : " + message.getClass());
            this.getOutputStream().writeObject(message);
        } catch (IOException e) {
            logger.error("Error while sending message", e);
        }
    }

    public void disconnect() {
        if (!this.isConnected()) return;
        logger.info("Disconnecting client");
        try {
            this.getOutputStream().close();
            this.getInputStream().close();
            this.getSocket().close();
        } catch (IOException e) {
            logger.error("Error while closing input stream", e);
        }
    }
}
