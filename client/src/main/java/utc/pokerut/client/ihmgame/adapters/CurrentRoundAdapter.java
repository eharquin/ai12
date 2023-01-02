package utc.pokerut.client.ihmgame.adapters;

import utc.pokerut.client.ihmgame.GameViewController;
import utc.pokerut.common.dataclass.Round;

public class CurrentRoundAdapter {

    private GameViewController gameViewController;

    public CurrentRoundAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private Round currentRound;

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void initCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
    }

    public void updateCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
    }

    public void endCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
    }

}
