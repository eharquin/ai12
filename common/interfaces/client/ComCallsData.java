package src.fr.utc.pokerut.common.interfaces.client;

import src.fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.UUID;

public interface ComCallsData {
    public void connexionUser(ServerProfile profile, String IP, int port);
    public void logoutUser(UUID playerId);
    public void modifyUser(ServerProfile profile);
}
