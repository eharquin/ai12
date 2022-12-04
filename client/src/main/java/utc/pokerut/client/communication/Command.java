package utc.pokerut.client.communication;

import java.io.Serializable;

public interface Command {
    void execute(Serializable payLoad);
}
