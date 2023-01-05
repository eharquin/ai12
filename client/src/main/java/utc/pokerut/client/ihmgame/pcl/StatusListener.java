package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.StatusAdapter;
import utc.pokerut.common.dataclass.StatusEnum;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StatusListener implements PropertyChangeListener {

    private StatusAdapter statusAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

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

    public StatusListener(StatusAdapter statusAdapter){
        this.statusAdapter = statusAdapter;
    }


}
