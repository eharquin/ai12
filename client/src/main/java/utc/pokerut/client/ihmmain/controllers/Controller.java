package utc.pokerut.client.ihmmain.controllers;

import javafx.fxml.Initializable;
import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.common.dataclass.ClientProfile;

public abstract class Controller {
    public static Core getCore() {
        return core;
    }

    public static void setCore(Core _core) {
        core = _core;
    }

    protected static Core core;

}
