package utc.pokerut.client.ihmgame.adapters;

import javafx.application.Platform;
import utc.pokerut.client.ihmgame.MappingImage;
import utc.pokerut.client.ihmgame.controllers.GameViewController;
import utc.pokerut.common.dataclass.Round;

import java.util.UUID;

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

        this.mappingImage = new MappingImage();

        UUID current_uuid = round.getCurrentPlayer().getId();
        UUID next_uuid = new UUID(0,0);
        UUID my_uuid = GameViewController.getCore().getGameCallsData().getConnectedPlayer().getId();

        //Trouver le prochain joueur, cela servira à déterminer quand on doit réactiver les boutons d'un joueur
        for (int i=1 ; i <= round.getHands().size() ; i++){
            if (round.getHands().get(i).getPlayer().getId() == current_uuid){
                next_uuid = round.getHands().get(i+1).getPlayer().getId();
            }
        }

        //On itère sur chaque joueur
        for (int i=1 ; i <= round.getHands().size(); i++){

            //Si c'est nous qui vons de jouer, il faut actualiser les cartes à afficher
            if (round.getCurrentPlayer().getId() == my_uuid){

                // Initialisation des cartes des joueurs
                for (int j=1 ; j <= 2 ; j++){
                    gameViewController.setPlayerCardsImageArray(mappingImage.mapping( round.getHandCurrentPlayer().getCards().get(i)), i, j);
                }
                //Désactiver les boutons du joueur qui vient de jouer (disable = true)
                gameViewController.setAreActionsDisabled(true);
            }
            else{
                //Si je suis le joueur suivant, réactiver mes boutons (disable = false)
                if (next_uuid == my_uuid){
                    gameViewController.setAreActionsDisabled(false);

                }
            }

            //Initialisation des crédits de chaque joueur
            gameViewController.setPlayerCredits( (Integer.toString(round.getHandCurrentPlayer().getAvailablePoints())), i );

            //Initialisation de la mise de chaque joueur
            gameViewController.setPlayersBettings( (Integer.toString(round.getCurrentBettingRound())), i);
        }

        //Màj les cartes du centre
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
