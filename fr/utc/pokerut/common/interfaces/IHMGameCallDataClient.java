package fr.utc.pokerut.common.interfaces;

import fr.utc.pokerut.common.dataclass.Action;
import fr.utc.pokerut.common.dataclass.Game;
import fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.List;
import java.util.UUID;

public interface IHMGameCallDataClient {
    public void displayPossibleActions(List<Action> actions);
    public void newPlayerJoinedDataGameOthers(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser);
}
