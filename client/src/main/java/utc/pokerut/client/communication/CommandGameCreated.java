package utc.pokerut.client.communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CommandGameCreated extends Command {
    public void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in) {
        System.out.println("User logged in : " + payLoad);
    }
}
