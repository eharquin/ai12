package utc.pokerut.server.communication;

import java.io.Serializable;

public class CommandLogOut extends Command{
    void execute(Serializable payLoad)
    {
        // Missing from the ComCallsData interface, present in the sequence diagram
        this.core.comCallsData.RemoveUserFromGame(payLoad[0], payLoad[1]);
    }
}
