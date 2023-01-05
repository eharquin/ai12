package utc.pokerut.client.ihmgame;

import utc.pokerut.client.ihmgame.Core;

public abstract class Controller {
    public static Core getCore() {
        return core;
    }

    public static void setCore(Core _core) {
        core = _core;
    }

    protected static Core core;

}
