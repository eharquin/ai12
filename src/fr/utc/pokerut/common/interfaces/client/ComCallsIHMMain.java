package src.fr.utc.pokerut.common.interfaces.client;

import src.fr.utc.pokerut.common.dataclass.*;

import java.util.UUID;

public interface ComCallsIHMMain {
    public void displayProfile(ServerProfile serverProfile);
    public void backToMainScreen(int nbCreditFinal, boolean resultat);
    public boolean notifyRejectionComMainCli(UUID userUuid, UUID gameUuid);
}
