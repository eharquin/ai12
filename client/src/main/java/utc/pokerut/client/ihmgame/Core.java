package utc.pokerut.client.ihmgame;

import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.client.ihmgame.implementations.ComCallsIHMGameImp;
import utc.pokerut.client.ihmgame.implementations.DataCallsIHMGameImp;
import utc.pokerut.client.ihmgame.implementations.IHMMainCallsIHMGameImp;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.interfaces.client.IHMGameCallsData;

public class Core {

    private ComCallsIHMGameImp comInterface;

    private DataCallsIHMGameImp dataInterface;

    private IHMMainCallsIHMGameImp mainInterface;

    private IHMGameCallsData dataCalls;

    private Game game;

    public ComCallsIHMGameImp getComInterface() {
        return comInterface;
    }

    public void setComInterface(ComCallsIHMGameImp comInterface) {
        this.comInterface = comInterface;
    }

    public DataCallsIHMGameImp getDataInterface() {
        return dataInterface;
    }

    public void setDataInterface(DataCallsIHMGameImp dataInterface) {
        this.dataInterface = dataInterface;
    }

    public IHMMainCallsIHMGameImp getMainInterface() {
        return mainInterface;
    }

    public void setMainInterface(IHMMainCallsIHMGameImp mainInterface) {
        this.mainInterface = mainInterface;
    }

    public void setGameCallsData(IHMGameCallsData dataCalls)
    {
        this.dataCalls = dataCalls;
    }

    public IHMGameCallsData getGameCallsData()
    {
        return dataCalls;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //constructor
    public Core() {

        GameViewController.setCore(this);

        setComInterface(new ComCallsIHMGameImp(this));
        setDataInterface(new DataCallsIHMGameImp(this));
        setMainInterface(new IHMMainCallsIHMGameImp(this));
    }



}
