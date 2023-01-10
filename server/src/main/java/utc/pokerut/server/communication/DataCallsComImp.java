package utc.pokerut.server.communication;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.DataCallsCom;
import utc.pokerut.common.messages.*;

import java.util.List;
import java.util.UUID;

public class DataCallsComImp implements DataCallsCom {

    private Core core;
    public DataCallsComImp(Core core) {
        this.core = core;
    }

    public ClientProfile AskForProfile(UUID playerID)
    {
        return null;
    }

    public ClientProfile AskForProfile(int playerID)
    {
        return null;
    }

    public void Connection(ServerProfile profile, String ip, int port)
    {

    }

    public void joinTableRequestDataComServ(UUID playerID, UUID gameID)
    {
        Game game = core.getComCallsData().getGameById(gameID);
        ClientHandler client = core.getServer().getClientById(game.getCreator().getId());

        client.send(new JoinGameAsked(playerID, gameID));
    }

    public void sendNewRound(Round round, List<Player> players)
    {
        core.getServer().broadcastPlayers(new UpdateNewRound(round), players);
    }

    public void sendNextPlayerActions(List<Action> actions, UUID playerID)
    {

    }

    public void sendUpdateRound(Round round, List<Player> players)
    {
        core.getServer().broadcastPlayers(new UpdateNewRound(round), players);
    }

    public void sendUpdateRoundAndEndResults(Round round, List<Player> players, List<Result> results)
    {
        core.getServer().broadcastPlayers(new UpdateRoundEnd(round, results), players);
    }

    public void sendUserActionsRefused(UUID playerID, Action action)
    {
        core.getServer().getClientById(playerID).send(new ActionRefused(action));
    }

    public void transmitLeaveMessage(UUID playerID, UUID gameID, int nbCreditFinal, boolean result)
    {

    }

    @Override
    public void addUserToGameDataComServ(Game game, Player player, UUID playerID) {

        List<ServerProfile> players = core.getComCallsData().getConnectedPlayers();
        for (ServerProfile p : players) {
            System.out.println("PlayerID : " + p.getId());
        }

        List<ClientHandler> clients = core.getServer().getClients();
        for (ClientHandler c : clients) {
            System.out.println("ClientID : " + c.getProfile().getId());
        }

        core.getServer().broadcastExcept(new PlayerJoinGame(game, player, playerID), playerID);
        core.getServer().getClientById(playerID).send(new JoinGameAccepted(game, player, playerID));
    }

    @Override
    public void launchGame(Game game) {
        core.getServer().broadcastPlayers(new LaunchGame(game), game.getPlayers());
    }
}
