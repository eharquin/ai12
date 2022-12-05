package utc.pokerut.client.communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import utc.pokerut.common.interfaces.client.DataCallsCom;

public class CommandUserLoggedIn extends Command {

    public void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in) {
        
    }
}
