package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.CurrentRoundAdapter;
import utc.pokerut.client.ihmgame.adapters.RoundsAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Round;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class CurrentRoundListener implements PropertyChangeListener {

    private CurrentRoundAdapter currentRoundAdapter;

    private GameViewController gameViewController;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        this.currentRoundAdapter = new CurrentRoundAdapter(gameViewController);

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

    public CurrentRoundListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
