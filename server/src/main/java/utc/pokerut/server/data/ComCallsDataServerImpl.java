package utc.pokerut.server.data;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.server.ComCallsData;

import java.util.*;

public class ComCallsDataServerImpl implements ComCallsData {
    private Core dataServerCore;

    public ComCallsDataServerImpl(Core dataServerCore) {
        this.dataServerCore = dataServerCore;
    }

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
    public void modify(ServerProfile profile) {

    }

    @Override
    public void sendUpdateRoundAndEndResult(Round round, ArrayList<Result> results, List<UUID> players) {

    }

    @Override
    public void sendNewRound(Round round, Round newRound, List<UUID> players) {

    }

    @Override
    public void saveUser(ServerProfile newUser) {
        dataServerCore.getConnectedPlayers().add(newUser);
    }

    @Override
    public ArrayList<Game> getWaitingGames() {
        return dataServerCore.getUnfilledWaitingGames();
    }

    @Override
    public ArrayList<ServerProfile> getConnectedPlayers() {
        return dataServerCore.getConnectedPlayers();
    }

    @Override
    public void initGameServer(Game newGame) {
        dataServerCore.getWaitingGames().add(newGame);
    }

    @Override
    public void askJoinTableComDataServ(UUID idUser, UUID idGame) {
        if (checkJoiningConditions(idUser, idGame) == true) {
            ServerProfile player = dataServerCore.getConnectedPlayer(idUser);
            Game game = dataServerCore.getUnfilledWaitingGame(idGame);
            if(game != null) {
                game.getPlayers().add(player);
                dataServerCore.getiDataCallsCom().joinTableRequestDataComServ(idUser, idGame);
            }
        }
    }

    /**
     * Returns true if a player can join the game
     * @param idUser : id of the user who wants to join the game
     * @param idGame : id of the game to be joined
     * @return
     */
    private boolean checkJoiningConditions(UUID idUser, UUID idGame){
        Game game = dataServerCore.getWaitingGame(idGame);
        //check that the game exists
        if(game == null)
            return false;
        //checks that the maximum number of players has not been reached
        if(game.getNbMaxPlayers() == game.getPlayers().size())
            return false;
        Player player = dataServerCore.getPlayerInGame(idUser, game);
        //checks that the player is not already in the game
        if(player != null)
            return false;
        return true;
    }

    @Override
    public void newPlayerJoinedComDataServ(UUID idUser, UUID idGame) {
        Game game = dataServerCore.getWaitingGame(idGame);
        Player player = dataServerCore.getConnectedPlayer(idUser);
        dataServerCore.getiDataCallsCom().addUserToGameDataComServ(game, player, idUser);
    }

    @Override
    public void startGame(UUID gameId){
        // move game from waiting game to onGoingGames
        Game game = dataServerCore.getWaitingGame(gameId);
        dataServerCore.getOnGoingGames().add(game);
        dataServerCore.getWaitingGames().remove(game);

        Collections.shuffle(game.getPlayers()); // mélanger la liste des joueurs

        initRound(game);

        // send next player actions
        List<Action> actions = dataServerCore.getGameEngine().actionCalulation();
        game = dataServerCore.getOnGoingGame(game.getId()); //majGame si jamais
        UUID nextPlayerId = game.getCurrentRound().getCurrentPlayer().getId();
        dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions,nextPlayerId);
    }

    @Override
    public void applyAction(UUID idPlayer, UUID idGame, Action action) {
        Round round = dataServerCore.getOnGoingGame(idGame).getCurrentRound();
        // update actionList
        round.getActions().add(action);
        //update Hand
        Hand currentPlayerHand = round.getHandByPlayerId(idPlayer);
        //update isFold
        if (action.getType().equals(ActionTypeEnum.FOLD)){
            currentPlayerHand.setIsFold(true);
            //update nbActivePlayers
            round.setNbActivePlayers(round.getNbActivePlayers()-1);
        }
        //updateAvailablePoints
        Integer oldAvailablePoints = currentPlayerHand.getAvailablePoints();
        currentPlayerHand.setAvailablePoints(oldAvailablePoints + action.getBetting());
        //update currentBets

        int oldBet = round.getCurrentBets().get(round.getCurrentBettingRound());
        round.getCurrentBets().put(round.getCurrentBettingRound(), oldBet+ action.getBetting());
        
        //update currentBet
        if (!action.getType().equals(ActionTypeEnum.ALL_IN)){
            round.setCurrentBet(action.getBetting());
        }

        Game game = this.dataServerCore.getOnGoingGame(idGame);
        //check if bettingRound is finished
        // if true
        updateBettingRound(action, round);
        if(isBettingRoundFinished(round)){
            round.setCurrentBettingRound(round.getCurrentBettingRound()+1);
            if(round.getCurrentBettingRound() == Round.NB_MAX_BETTING_ROUND) {
                // la partie est finie
                // envoyer la fin du round au joueur ?
                this.dataServerCore.getiDataCallsCom().sendUpdateRound(round, game.getPlayers());

            } else {
                // la partie n'est pas finie

                // envoyer le nouveau round vérifier si c'est la bonne version
                initRound(game);
                this.dataServerCore.getiDataCallsCom().sendNewRound(round, game.getPlayers());

                List<Action> actions = this.dataServerCore.getGameEngine().actionCalculation();
                this.dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions, round.getCurrentPlayer().getId());
            }
        } else {
            this.setNextPlayerRound(round);
            this.dataServerCore.getiDataCallsCom().sendUpdateRound(round, game.getPlayers());

            List<Action> actions = this.dataServerCore.getGameEngine().actionCalculation();
            this.dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions, round.getCurrentPlayer().getId());
            // envoyer le round
        }

        //check is RoundFinished
        // if true
        // update currentRound
        //if false
        //update currentPlayer

        //lui envoyer les actions

        // if currentBettingRound == 0
        // aucune carte de reoturner, showedCard vide
        //if currentBettingRound == 1, une carte de la pioche est brulée, trois cartes de retournées
        // if currentBettingRound == 2, la première carte est jetée, la deuxième est retournée
        // if currentBettingRound == 3, la première carte est jetée, la deuxième est retournée

    }

    public void updateBettingRound(Action action, Round round) {
        if(action.equals(ActionTypeEnum.CHECK)) { // si l'action du joueur en cours == check
            round.setNbCheckSuccessivePlayers(round.getNbCheckSuccessivePlayers()+1);
        } else {
            round.setNbCheckSuccessivePlayers(0);
        }

        if(action.equals(ActionTypeEnum.CALL)) { // si l'action du joueur en cours == call
            round.setNbCallSuccessivePlayers(round.getNbCallSuccessivePlayers()+1);
        } else if (action.equals(ActionTypeEnum.RAISE)) { // si l'action du joueur en cours == RAISE
            round.setNbCallSuccessivePlayers(1);
        } else if(action.equals(ActionTypeEnum.BET)) { // si l'action du joueur en cours == BET
            round.setNbCallSuccessivePlayers(1);
        } else {
            round.setNbCallSuccessivePlayers(0);
        }
    }


    public boolean isBettingRoundFinished(Round round) {
        if (round.getNbCallSuccessivePlayers() == round.getNbActivePlayers() || round.getNbCheckSuccessivePlayers() == round.getNbActivePlayers() //
        || round.getNbActivePlayers() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void initRound(Game game){
        // init new Round
        Round round = new Round(game.getPlayers().get(0));
        dataServerCore.getiDataCallsCom().sendNewRound(round, game.getPlayers());


        // payer petite blinde
        int littleBlinde = Math.round(game.getNbPoints()/100);
        Action actionPayerPetiteBlinde = new Action(ActionTypeEnum.BET, littleBlinde, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerPetiteBlinde);

        // payer la grosse blinde
        int grosseBlincde = littleBlinde*2;
        // éventuellment peut poser problème apply action
        //dataServerCore.setNextPlayerRound(game.getPlayers(), game.getCurrentRound());
        game = dataServerCore.getOnGoingGame(game.getId()); // histoire d'être sur que ce soit bien à jour
        setNextPlayerRound(round);
        Action actionPayerGrosseBlinde = new Action(ActionTypeEnum.BET, grosseBlincde, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerGrosseBlinde);

    }

    public void initRound(Game game, Player player){
        // init new Round
        Round round = new Round(player);
        dataServerCore.getiDataCallsCom().sendNewRound(round, game.getPlayers());


        // payer petite blinde
        int littleBlinde = Math.round(game.getNbPoints()/100);
        Action actionPayerPetiteBlinde = new Action(ActionTypeEnum.BET, littleBlinde, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerPetiteBlinde);

        // payer la grosse blinde
        int grosseBlincde = littleBlinde*2;
        // éventuellment peut poser problème apply action
        //dataServerCore.setNextPlayerRound(game.getPlayers(), game.getCurrentRound());
        game = dataServerCore.getOnGoingGame(game.getId()); // histoire d'être sur que ce soit bien à jour
        setNextPlayerRound(round);
        Action actionPayerGrosseBlinde = new Action(ActionTypeEnum.BET, grosseBlincde, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerGrosseBlinde);

    }
    public void setNextPlayerRound(Round round){
        for(int i=0; i<round.getHands().size(); i++) {
            int indexNextPlayer = (i+1)%round.getHands().size();
            if(round.getHands().get(i).getPlayer().getId() == round.getCurrentPlayer().getId() //
            && !round.getHands().get(indexNextPlayer).getIsFold()) {
                round.setCurrentPlayer(round.getHands().get(indexNextPlayer).getPlayer());
                return ;
            }
        }

    }


	@Override
    public void removeUser(UUID playerDisconnectingId) {
        this.dataServerCore.removeConnectedPlayer(playerDisconnectingId);
    }
}