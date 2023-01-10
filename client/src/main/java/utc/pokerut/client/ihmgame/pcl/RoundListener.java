package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.RoundsAdapter;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Result;
import utc.pokerut.common.dataclass.Round;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
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

            switch (actions[0]){
                case "init":
                    ArrayList<Round> init_rounds = (ArrayList<Round>) event.getNewValue();
                    roundsAdapter.initRounds(init_rounds);
                    break;
                case "add":
                    ArrayList<Round> add_rounds = (ArrayList<Round>) event.getOldValue();
                    roundsAdapter.addRounds(add_rounds);
                    break;
            }
        }
    }

    public RoundListener(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }


}
