package utc.pokerut.server.communication;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Command {
    protected Core core;

    void Command(Core core){
        this.core = core;
    }
    abstract void execute(Serializable payLoad, ObjectOutputStream out, ObjectInputStream in);
}
