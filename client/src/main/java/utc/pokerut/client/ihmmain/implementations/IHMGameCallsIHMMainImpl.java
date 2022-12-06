package utc.pokerut.client.ihmmain.implementations;

import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.interfaces.client.IHMGameCallsIHMMain;

public class IHMGameCallsIHMMainImpl implements IHMGameCallsIHMMain {
    private Core core;
    @Override
    public void backToMainScreen() {
        core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
    }
    public IHMGameCallsIHMMainImpl(Core core)
    {
        this.core = core;
    }
}
