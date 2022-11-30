package utc.pokerut.client.ihmmain.listeners;

import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayerListListener implements PropertyChangeListener {
    private MainController mainController;
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt != null)
        {
            String action = evt.getPropertyName();
            String[] actions = action.split("_");
            List<Player> playerList = (List<Player>) evt.getOldValue();

            switch (actions[0])
            {
                case "init":
                    mainController.setPlayerList(playerList);
                    break;
                case "add":
                    mainController.setPlayerList(playerList);
                    break;
                case "remove":
                    mainController.setPlayerList(playerList);
                    break;
            }
        }
    }
    public PlayerListListener(MainController controller)
    {
        mainController = controller;
    }
}

