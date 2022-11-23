package src.fr.utc.pokerut.common.interfaces.client;

import src.fr.utc.pokerut.common.dataclass.Game;

public interface DataCallsIHMGame{

    public void notifyNewRoundUpdate(){}

    public void newGameWindow(List<Action> actions){}


}