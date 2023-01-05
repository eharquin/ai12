package utc.pokerut.client.ihmgame.pcl;

import utc.pokerut.client.ihmgame.adapters.ResultsAdapter;
import utc.pokerut.common.dataclass.Result;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ResultsListener implements PropertyChangeListener {

    private ResultsAdapter resultsAdapter;

    @Override
    public void propertyChange(PropertyChangeEvent event){

        if(event != null){

            String action = event.getPropertyName();
            String[] actions = action.split("_");
            List<Result> results = (List<Result>) event.getNewValue();

            switch (actions[0]){
                case "init":
                    resultsAdapter.initResults(results);
                    break;
                case "add":
                    resultsAdapter.addResults(results);
                    break;
                case "remove":
                    resultsAdapter.removeResults(results);
                    break;
            }
        }
    }

    public ResultsListener(ResultsAdapter resultsAdapter){
        this.resultsAdapter = resultsAdapter;
    }


}
