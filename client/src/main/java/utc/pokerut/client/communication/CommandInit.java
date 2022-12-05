package utc.pokerut.client.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.messages.client.Message;

public class CommandInit extends Command {

    public void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in) {
        

        ArrayList<Game> games = (ArrayList<Game>) payLoad;

        try {
            Message m = (Message) in.readObject();

            // list player (from ServerProfile)
            ArrayList<Player> players = (ArrayList<Player>) payLoad;
            this.core.comCallsData.sendLists(players, games);
            
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
