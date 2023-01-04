package utc.pokerut.common.interfaces.client;

import utc.pokerut.common.dataclass.Action;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;
import utc.pokerut.common.dataclass.ServerProfile;

import java.util.List;
import java.util.UUID;

public interface IHMGameCallsData {
    public Player getConnectedPlayer();
    public Game getGame();
}
