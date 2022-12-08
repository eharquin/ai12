package utc.pokerut.client.ihmmain.listeners;

import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.client.ihmmain.controllers.PlayerListController;
import utc.pokerut.common.dataclass.Player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayerListListener implements PropertyChangeListener {
    private PlayerListController playerListController;
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt != null)
        {
            String action = evt.getPropertyName();
            String[] actions = action.split("_");
            System.out.println("Message received in PropertyChange : " + evt.getPropertyName());
            switch (actions[0])
            {
                case "init":
                    List<Player> playerList = (List<Player>) evt.getNewValue();
                    playerListController.setPlayerList(playerList);
                    break;
                case "add":
                    Player player = (Player) evt.getNewValue();
                    playerListController.addPlayerList(player);
                    break;
                case "remove":
                    //playerListController.setPlayerList(playerList);
                    break;
            }
        }
    }
    public PlayerListListener(PlayerListController controller)
    {
        playerListController = controller;
    }
}

