package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.ResultsAdapter;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.Result;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ResultsListener implements PropertyChangeListener {

    private ResultsAdapter resultsAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");

            switch (actions[0]){
                case "init":
                    ArrayList<Result> init_results = (ArrayList<Result>) event.getNewValue();
                    resultsAdapter.initResults(init_results);
                    break;
                case "add":
                    ArrayList<Result> add_results = (ArrayList<Result>) event.getOldValue();
                    resultsAdapter.addResults(add_results);
                    break;
                case "remove":
                    ArrayList<Result> remove_results = (ArrayList<Result>) event.getOldValue();
                    resultsAdapter.removeResults(remove_results);
                    break;
            }
        }
    }

    public ResultsListener(ResultsAdapter resultsAdapter){
        this.resultsAdapter = resultsAdapter;
    }


}
