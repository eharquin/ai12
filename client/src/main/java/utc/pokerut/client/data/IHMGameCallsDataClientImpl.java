package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.interfaces.client.IHMGameCallsData;

public class IHMGameCallsDataClientImpl implements IHMGameCallsData {

    private Core myDataCore;

    public IHMGameCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

   @Override
    public Player getConnectedPlayer() {
        return myDataCore.getProfile();
   }

    @Override
    public Game getGame() {
        return myDataCore.getCurrentGame();
    }

}
