package utc.pokerut.client.ihmgame.implementations;

import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.interfaces.client.IHMMainCallsIHMGame;

public class IHMMainCallsIHMGameImp implements IHMMainCallsIHMGame {

    private Core core;

    public IHMMainCallsIHMGameImp(Core core){ this.core = core; }

    @Override
    public void createGame(Game new_game){}

    @Override
    public void newGameWindow(Game gameNewPlayer){}

//    public void playReplay(Replay replay);

}
