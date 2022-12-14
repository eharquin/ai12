package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
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
}
