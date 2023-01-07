package utc.pokerut.client.ihmmain.listeners;

import javafx.application.Platform;
import utc.pokerut.client.ihmmain.controllers.Controller;
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
                // We used here Platform runlater to avoid IllegalStateException (when we call GUI methods not on FX app thread)
                case "init":
                    Platform.runLater(() -> {
                        List<Player> playerList = (List<Player>) evt.getNewValue();
                        playerListController.setPlayerList(playerList);
                    });

                    break;
                case "add":
                    Platform.runLater(() -> {
                        Player player = (Player) evt.getNewValue();
                        playerListController.addPlayerList(player);
                    });

                    break;
                case "remove":
                    Platform.runLater(() ->{
                        Player player1 = (Player) evt.getNewValue();
                        playerListController.removePlayerList(player1);
                    });
                    break;
            }
        }
    }
    public PlayerListListener(PlayerListController controller)
    {
        playerListController = controller;
    }
}

