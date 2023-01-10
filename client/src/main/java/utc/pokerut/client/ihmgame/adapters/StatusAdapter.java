package utc.pokerut.client.ihmgame.adapters;

import javafx.application.Platform;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.StatusEnum;

public class StatusAdapter {

    private GameViewController gameViewController;

    public StatusAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private StatusEnum status;

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void initStatus(StatusEnum status){
        //@TODO: Update game scene in GameViewController
        Platform.runLater(() ->{

        });
    }

    public void updateStatus(StatusEnum status){
        //@TODO: Update game scene in GameViewController
        Platform.runLater(() ->{

        });
    }

}
