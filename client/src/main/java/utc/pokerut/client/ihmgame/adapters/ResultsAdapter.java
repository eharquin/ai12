package utc.pokerut.client.ihmgame.adapters;

import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Result;

import java.util.List;

public class ResultsAdapter {

    private GameViewController gameViewController;

    public ResultsAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private List<Result> resultsList;

    public List<Result> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<Result> resultsList) {
        this.resultsList = resultsList;
    }

    public void initResults(List<Result> results){
        //@TODO: Update game scene in GameViewController
    }

    public void addResults(List<Result> results){
        //@TODO: Update game scene in GameViewController
    }

    public void removeResults(List<Result> results){
        //@TODO: Update game scene in GameViewController
    }

}
