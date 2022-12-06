package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Game;

public interface IHMMainCallsIHMGame{

    public void createGame(Game new_game);

    public void newGameWindow(Game gameNewPlayer);

//    public void playReplay(Replay replay);

}