package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.client.IHMGameCallsData;

import java.util.List;
import java.util.UUID;

public class IHMGameCallsDataClientImpl implements IHMGameCallsData {
    private Core myDataCore;

    /**
     *
     * @param myDataCore
     */
    public IHMGameCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

    /**
     *
     * @param actions
     */
    @Override
    public void displayPossibleActions(List<Action> actions) {

    }

    /**
     *
     * @param gameNewPlayer
     * @param newPlayer
     * @param idUser
     */
    @Override
    public void newPlayerJoinedDataGameOthers(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser) {

    }

    /**
     *
     * @param playerID
     * @param gameID
     * @param action
     */
    @Override
    public void sendAction(UUID playerID, UUID gameID, Action action) {
        myDataCore.getiDataCallsCom().sendAction(playerID, gameID, action);
    }

    /**
     *
     * @return
     */
    @Override
    public Player getConnectedPlayer() {
        return myDataCore.getProfile();
   }

    /**
     *
     * @return
     */
    @Override
    public Game getGame() {
        return myDataCore.getCurrentGame();
    }
}
