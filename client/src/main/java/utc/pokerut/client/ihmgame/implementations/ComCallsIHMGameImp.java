package utc.pokerut.client.ihmgame.implementations;

import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.common.dataclass.ChatMessage;
import utc.pokerut.common.interfaces.client.ComCallsIHMGame;

public class ComCallsIHMGameImp implements ComCallsIHMGame {

    private Core core;

    public ComCallsIHMGameImp(Core core) { this.core = core; }

    @Override
    public void displayMessage(ChatMessage msg){}

}
