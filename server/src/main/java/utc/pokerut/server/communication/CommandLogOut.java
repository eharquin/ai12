package utc.pokerut.server.communication;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommandLogOut extends Command{
    void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in)
    {
        // Missing from the ComCallsData interface, present in the sequence diagram
        this.core.comCallsData.RemoveUserFromGame(payLoad[0], payLoad[1]);
    }
}
