package utc.pokerut.client.ihmmain.controllers;

import javafx.application.Platform;
import utc.pokerut.client.ihmmain.ViewNames;

import static utc.pokerut.client.ihmmain.controllers.Controller.core;

public class LogoutController {

    public void reconnect() {
        core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
    }

    public void closeApp() {
        Platform.exit();
    }
}
