package utc.pokerut.client.ihmmain.listeners;

import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.common.dataclass.Game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class GameListListener implements PropertyChangeListener {
    private MainController mainController;
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
                    mainController.setGameList(gameList);
                    break;
                case "add":
                    mainController.setGameList(gameList);
                    break;
                case "remove":
                    mainController.setGameList(gameList);
                    break;
            }
        }
    }

    public GameListListener(MainController controller)
    {
        mainController = controller; //controller tablesView fonction setGameList
    }

}








