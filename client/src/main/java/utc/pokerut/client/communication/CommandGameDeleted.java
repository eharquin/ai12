package utc.pokerut.client.communication;

import java.io.Serializable;

public class CommandGameDeleted implements Command {

    @Override
    public void execute(Serializable payLoad) {
        System.out.println("User logged in : " + payLoad);
    }
}
