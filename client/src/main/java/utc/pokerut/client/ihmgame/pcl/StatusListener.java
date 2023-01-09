package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.StatusAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.StatusEnum;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatusListener implements PropertyChangeListener {

    private GameViewController gameViewController;

    private StatusAdapter statusAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        this.statusAdapter = new StatusAdapter(gameViewController);

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");
            StatusEnum status = (StatusEnum) event.getNewValue();

            switch (actions[0]){
                case "init":
                    statusAdapter.initStatus(status);
                    break;
                case "update":
                    statusAdapter.updateStatus(status);
                    break;
            }
        }
    }

    public StatusListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
