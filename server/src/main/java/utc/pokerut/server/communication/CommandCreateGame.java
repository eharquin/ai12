package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.Game;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommandCreateGame extends Command{
    public void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in)
    {
        this.core.comCallsData.initGameServer((Game) payLoad);
    }
}
