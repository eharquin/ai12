package utc.pokerut.server.communication;

import java.io.Serializable;

public abstract class Command {
    protected Core core;

    void Command(Core core){
        this.core = core;
    }
    abstract void execute(Serializable payLoad);
}
