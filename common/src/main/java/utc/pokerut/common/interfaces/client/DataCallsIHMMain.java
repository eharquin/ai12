package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Game;

import java.util.UUID;

public interface DataCallsIHMMain {
    public void displayGame(Game game);

    public boolean joinTableRequestComGameCreator(String username, UUID gameID);
}
