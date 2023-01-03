package utc.pokerut.client.ihmmain.listeners;

import utc.pokerut.client.ihmmain.controllers.MainController;
import utc.pokerut.client.ihmmain.controllers.WaitingRoomController;
import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javafx.event.Event;

public class LobbyGameListener implements PropertyChangeListener{
    private WaitingRoomController waitingRoomController;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // quelle action faire (ini, ajout, remove?)
        if (evt != null) {
            String action = evt.getPropertyName();

            switch (action) {
                case "joinRequest":
                    ClientProfile requester = (ClientProfile)evt.getNewValue();
                    break;
                case "approveRequest":
                    break;
                case "removeRequest":
                    break;
            }
        }
    }

}
