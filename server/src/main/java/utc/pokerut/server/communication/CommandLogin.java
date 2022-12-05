package utc.pokerut.server.communication;

// pokerut
import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.messages.server.*;

import java.io.Serializable;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommandLogin extends Command{
    void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in)
    {
        // receive server profile 
        ServerProfile profile = (ServerProfile) payLoad;

        // Missing from the ComCallsData interface, present in the sequence diagram
        this.core.comCallsData.saveUser(profile);
        this.core.comCallsData.askJoinTableComDataServ(payLoad[0], payLoad[1]);

        ArrayList<Game> games = this.core.comCallsData.getWaitingGames();
        ArrayList<ServerProfile> players = this.core.comCallsData.getConnectedPlayers();

        // send all game and all player profile to the new user
        Message m = new Message(MessageType.Init, games);
        out.writeObject(m);

        m = new Message(MessageType.Init, players);
        out.writeObject(m);


    }
}
