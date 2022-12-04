package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.Game;

import java.io.Serializable;

public class CommandCreateGame extends Command{
    public void execute(Serializable payLoad)
    {
        this.core.comCallsData.initGameServer((Game) payLoad);
    }
}
