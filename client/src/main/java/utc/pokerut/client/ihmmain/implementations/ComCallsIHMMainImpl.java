package utc.pokerut.client.ihmmain.implementations;

import utc.pokerut.client.ihmmain.Core;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.ComCallsIHMMain;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.UUID;

public class ComCallsIHMMainImpl implements ComCallsIHMMain {
    private Core core;
    @Override
    public void displayProfile(ServerProfile serverProfile) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void backToMainScreen(int nbCreditFinal, boolean resultat) {
        core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
    }

    @Override
    public boolean notifyRejectionComMainCli(UUID userUuid, UUID gameUuid) {
        Alert alert = new Alert(AlertType.ERROR);
        // set alert type, title, content text and then show it
        alert.setTitle("Erreur de connexion");
        alert.setContentText("La connexion a été rejetée depuis le module de communication !");
        alert.show();
        return true;
    }
    public ComCallsIHMMainImpl(Core core)
    {
        this.core = core;
    }
}
