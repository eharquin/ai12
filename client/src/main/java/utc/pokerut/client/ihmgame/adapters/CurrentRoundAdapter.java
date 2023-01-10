package utc.pokerut.client.ihmgame.adapters;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utc.pokerut.client.ihmgame.MappingImage;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Round;

public class CurrentRoundAdapter {

    private GameViewController gameViewController;

    public CurrentRoundAdapter(GameViewController gameViewController){
        this.gameViewController = gameViewController;
    }

    private Round currentRound;

    private MappingImage mappingImage;

    public Round getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Round currentRound) {
        this.currentRound = currentRound;
    }

    public void initCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
        Platform.runLater(() ->{

        });
    }

    public void updateCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
        this.mappingImage = new MappingImage();

        for (int i=1 ; i <= gameViewController.getGame().getPlayers().size(); i++){

            if (round.getCurrentPlayer().getId() == GameViewController.getCore().getGameCallsData().getConnectedPlayer().getId()){
                // Initialisation des cartes des joueurs
                for (int j=1 ; j <= 2 ; j++){
                    gameViewController.setPlayerCardsImageArray(mappingImage.mapping( round.getHandCurrentPlayer().getCards().get(i)), i, j);
                }
                gameViewController.setAreActionsAvailable(true);
            }
            else{
                gameViewController.setAreActionsAvailable(false);
            }

            //Initialisation des crédits de chaque joueur
            gameViewController.setPlayerCredits( (Integer.toString(round.getHandCurrentPlayer().getAvailablePoints())), i );

            //Initialisation de la mise de chaque joueur
            gameViewController.setPlayersBettings( (Integer.toString(round.getCurrentBettingRound())), i);
        }

        for (int i=0 ; i <= round.getShowedCards().size() ; i++){
            gameViewController.setCenterCardsImageArray(mappingImage.mapping(round.getShowedCards().get(i)), i);
        }

    }

    public void endCurrentRound(Round round){
        //@TODO: Update game scene in GameViewController
        Platform.runLater(() ->{

        });
    }

}
