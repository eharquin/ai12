package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.client.IHMGameCallsData;

import java.util.List;
import java.util.UUID;

public class IHMGameCallsDataClientImpl implements IHMGameCallsData {
    private Core myDataCore;
    public IHMGameCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

    @Override
    public void displayPossibleActions(List<Action> actions) {

    }

    @Override
    public void newPlayerJoinedDataGameOthers(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser) {

    }

    @Override
    public void sendAction(UUID playerID, UUID gameID, Action action) {
        myDataCore.getiDataCallsCom().sendAction(playerID, gameID, action);
    }

    @Override
    public Player getConnectedPlayer(){
       return myDataCore.getProfile();
   }

    @Override
    public Game getGame(){
        return myDataCore.getCurrentGame();
    }
}
