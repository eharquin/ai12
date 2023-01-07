package utc.pokerut.client.ihmmain.listeners;

import javafx.application.Platform;
import utc.pokerut.client.ihmmain.controllers.GameListController;
import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.common.dataclass.Game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class GameListListener implements PropertyChangeListener {
    private GameListController gameListController;
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         //quelle action faire (ini, ajout, remove?)
        if(evt != null)
        {
            String action = evt.getPropertyName();
            String[] actions = action.split("_");

            switch (actions[0])
            {
                case "init":
                    Platform.runLater(() ->{
                        List<Game> gameList = (List<Game>) evt.getNewValue();
                        gameListController.setGameList(gameList);
                    });
                    break;
                case "add":
                    Platform.runLater(() ->{
                        Game game = (Game) evt.getNewValue();
                        gameListController.addGame(game);
                    });
                    break;
                case "remove":
                    Platform.runLater(() ->{
                        Game game1 = (Game) evt.getNewValue();
                        gameListController.removeGame(game1);
                    });
                    break;
            }
        }
    }

    public GameListListener(GameListController controller)
    {
        gameListController = controller; //controller tablesView fonction setGameList
    }

}








