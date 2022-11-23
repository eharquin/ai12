package src.fr.utc.pokerut.server.data;

import src.fr.utc.pokerut.common.dataclass.*;
import src.fr.utc.pokerut.common.interfaces.ComServerCallDataServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComCallDataServerImpl implements ComServerCallDataServer {
    @Override
    public void sendUpdateRound(Round round, List<UUID> Players) {

    }

    @Override
    public void sendUpdateNextRound(Round round, List<UUID> players) {

    }

    @Override
    public void sendNextPlayerActions(List<Action> actions, UUID nextPlayer) {

    }

    @Override
    public void sendUserActionRefused() {

    }

    @Override
    public void sendUpdateRoundAndNewRound(Round round, Round newRound, List<UUID> players) {

    }

    @Override
    public void transmitLeaveMessage(UUID idPlayer, UUID idGame, int nbCreditFinal, boolean result) {

    }

    @Override
    public void joinTableRequestDataComServ(UUID idUser, UUID idGame) {

    }

    @Override
    public void addUserToGameDataComServ(Game gameNewPlayer, Player newPlayer, UUID idUser) {

    }

    @Override
    public void modify(ServerProfile profile) {

    }

    @Override
    public void sendUpdateRoundAndEndResult(Round round, ArrayList<Result> results, List<UUID> players) {

    }

    @Override
    public void sendNewRound(Round round, Round newRound, List<UUID> players) {

    }
}
