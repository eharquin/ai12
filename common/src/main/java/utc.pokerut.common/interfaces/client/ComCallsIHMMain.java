package utc.pokerut.common.interfaces.client;


import java.util.UUID;

public interface ComCallsIHMMain {
    public void displayProfile(utc.pokerut.common.dataclass.ServerProfile serverProfile);
    public void backToMainScreen(int nbCreditFinal, boolean resultat);
    public boolean notifyRejectionComMainCli(UUID userUuid, UUID gameUuid);
}
