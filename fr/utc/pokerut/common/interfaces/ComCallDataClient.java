package fr.utc.pokerut.common.interfaces;

import fr.utc.pokerut.common.dataclass.ServerProfile;

import java.util.UUID;

public interface ComCallDataClient {
    public void connexionUser(ServerProfile profile, String IP, int port);
    public void logoutUser(UUID playerId);
    public void modifyUser(ServerProfile profile);
}
