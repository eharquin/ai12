package utc.pokerut.client.ihmmain.listeners;

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
            List<Game> gameList = (List<Game>) evt.getOldValue();

            switch (actions[0])
            {
                case "init":
                    gameListController.setGameList(gameList);
                    break;
                case "add":
                    gameListController.setGameList(gameList);
                    break;
                case "remove":
                    gameListController.setGameList(gameList);
                    break;
            }
        }
    }

    public GameListListener(GameListController controller)
    {
        gameListController = controller; //controller tablesView fonction setGameList
    }

}








