package utc.pokerut.server.communication;

import java.io.Serializable;

public interface Command {
    void execute(Serializable payLoad);
}
