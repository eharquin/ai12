package utc.pokerut.client.ihmmain.implementations;

import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.interfaces.client.DataCallsIHMMain;

public class DataCallsIHMMainImpl implements DataCallsIHMMain {
    private Core core;
    @Override
    public void displayGame(Game game) {
        core.getMainController().Navigate(ViewNames.IHM_GAME_VIEW);
    }
    public DataCallsIHMMainImpl(Core core)
    {
        this.core = core;
    }
}
