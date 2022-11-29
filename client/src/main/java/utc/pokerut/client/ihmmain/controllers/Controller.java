package utc.pokerut.client.ihmmain.controllers;

import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.common.dataclass.ClientProfile;

public abstract class Controller {
    public Core getCore() {
        return core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    protected Core core;

}
