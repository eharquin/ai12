package utc.pokerut.client.ihmgame.adapters;

import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Round;

import java.util.List;

public class RoundsAdapter {

    private GameViewController gameViewController;

    public RoundsAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    List<Round> roundsList;

    public List<Round> getRoundsList() {
        return roundsList;
    }

    public void setRoundsList(List<Round> roundsList) {
        this.roundsList = roundsList;
    }

    public void initRounds(List<Round> rounds){
        //@TODO: Update game scene in GameViewController
    }

    public void addRounds(List<Round> rounds){
        //@TODO: Update game scene in GameViewController
    }

}
