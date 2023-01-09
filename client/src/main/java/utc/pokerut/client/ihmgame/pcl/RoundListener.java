package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.RoundsAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Round;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RoundListener implements PropertyChangeListener {

    private GameViewController gameViewController;

    private RoundsAdapter roundsAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        this.roundsAdapter = new RoundsAdapter(gameViewController);

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");
            List<Round> rounds = (List<Round>) event.getNewValue();

            switch (actions[0]){
                case "init":
                    roundsAdapter.initRounds(rounds);
                    break;
                case "add":
                    roundsAdapter.addRounds(rounds);
                    break;
            }
        }
    }

    public RoundListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
