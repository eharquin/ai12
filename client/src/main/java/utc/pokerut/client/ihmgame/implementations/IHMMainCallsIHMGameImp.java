package utc.pokerut.client.ihmgame.implementations;

import utc.pokerut.client.ihmgame.Core;
import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.interfaces.client.IHMMainCallsIHMGame;

public class IHMMainCallsIHMGameImp implements IHMMainCallsIHMGame {

    private Core core;
    private GameViewController gameViewController;

    public IHMMainCallsIHMGameImp(Core core){ this.core = core; }

    @Override
    public void createGame(Game new_game){
        gameViewController = new GameViewController();
        gameViewController.initGameStatic(new_game);
    }

    @Override
    public void newGameWindow(Game gameNewPlayer){}

//    public void playReplay(Replay replay);

}
