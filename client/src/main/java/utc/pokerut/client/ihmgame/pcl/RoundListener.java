package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.RoundsAdapter;
import utc.pokerut.common.dataclass.Round;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RoundListener implements PropertyChangeListener {

    private RoundsAdapter roundsAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

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

    public RoundListener(RoundsAdapter roundsAdapter){
        this.roundsAdapter = roundsAdapter;
    }


}
