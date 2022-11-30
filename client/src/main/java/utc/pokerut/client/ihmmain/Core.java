package utc.pokerut.client.ihmmain;

import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.IHMGameCallsIHMMain;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;
import utc.pokerut.common.interfaces.client.IHMMainCallsIHMGame;

public class Core {
    private IHMMainCallsIHMGame gameInterface;
    private IHMMainCallsData dataInterface;
    private IHMGameCallsIHMMain gameCallsMainInterface;

    public IHMMainCallsIHMGame getGameInterface() {
        return gameInterface;
    }

    public void setGameInterface(IHMMainCallsIHMGame gameInterface) {
        this.gameInterface = gameInterface;
    }

    public IHMMainCallsData getDataInterface() {
        return dataInterface;
    }

    public void setDataInterface(IHMMainCallsData dataInterface) {
        this.dataInterface = dataInterface;
    }

    public IHMGameCallsIHMMain getGameCallsMainInterface() {
        return gameCallsMainInterface;
    }

    public void setGameCallsMainInterface(IHMGameCallsIHMMain gameCallsMainInterface) {
        this.gameCallsMainInterface = gameCallsMainInterface;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Player getPlayerConnected() {
        return playerConnected;
    }

    public void setPlayerConnected(Player playerConnected) {
        this.playerConnected = playerConnected;
    }

    private MainController mainController;

    private Player playerConnected;
    public Core(MainController controller)
    {
        this.mainController = controller;
    }

}
