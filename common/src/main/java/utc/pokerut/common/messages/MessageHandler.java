package utc.pokerut.common.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

abstract public class MessageHandler<T> {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    abstract public ObjectInputStream getInputStream();

    abstract public ObjectOutputStream getOutputStream();

    abstract protected T getCore();

    abstract protected Command getCommand(Class<? extends Command> commandClass);

    abstract public Boolean isConnected();

    protected HashMap<Class<? extends Message>, Class<? extends Command>> messages;

    public void run() {
        while (this.isConnected()) {
            Message message = null;
            try {
                message = (Message) this.getInputStream().readObject();
            } catch (Exception e) {
                logger.error("Error while reading message", e);
            }

            logger.info("Message received : " + message.getClass());
            Command command = this.getCommand(messages.get(message.getClass()));
            command.setCore(this.getCore());
            command.setMessage(message);
            logger.debug("Running command : " + command.getClass());
            command.execute();
            logger.debug("Message handled : " + message.getClass());
        }
    }

    public void send(Message message) {
        try {
            logger.info("Sending message : " + message.getClass());
            this.getOutputStream().writeObject(message);
        } catch (IOException e) {
            logger.error("Error while sending message", e);
        }
    }
}
