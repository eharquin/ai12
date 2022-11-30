package utc.pokerut.client.communication;

import java.io.Serializable;

public class CommandUserLoggedIn implements Command {

    @Override
    public void execute(Serializable payLoad) {
        System.out.println("User logged in : " + payLoad);
    }
}
