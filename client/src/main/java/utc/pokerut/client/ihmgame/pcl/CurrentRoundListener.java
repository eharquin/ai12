package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.CurrentRoundAdapter;
import utc.pokerut.client.ihmgame.adapters.RoundsAdapter;
import utc.pokerut.common.dataclass.Round;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class CurrentRoundListener implements PropertyChangeListener {

    private CurrentRoundAdapter currentRoundAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");
            Round round = (Round) event.getNewValue();

            switch (actions[0]){
                case "init":
                    currentRoundAdapter.initCurrentRound(round);
                    break;
                case "update":
                    currentRoundAdapter.updateCurrentRound(round);
                    break;
                case "end":
                    currentRoundAdapter.endCurrentRound(round);
                    break;
            }
        }
    }

    public CurrentRoundListener(CurrentRoundAdapter currentRoundAdapter){
        this.currentRoundAdapter = currentRoundAdapter;
    }


}
