package utc.pokerut.client.ihmgame;

import utc.pokerut.client.ihmgame.implementations.ComCallsIHMGameImp;
import utc.pokerut.client.ihmgame.implementations.DataCallsIHMGameImp;
import utc.pokerut.client.ihmgame.implementations.IHMMainCallsIHMGameImp;
import utc.pokerut.common.dataclass.Game;

public class Core {

    private ComCallsIHMGameImp comInterface;

    private DataCallsIHMGameImp dataInterface;

    private IHMMainCallsIHMGameImp mainInterface;

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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    //constructor
    public Core(Game game) {

        this.game = game;
        Controller.setCore(this);

        setComInterface(new ComCallsIHMGameImp(this));
        setDataInterface(new DataCallsIHMGameImp(this));
        setMainInterface(new IHMMainCallsIHMGameImp(this));
    }



}
