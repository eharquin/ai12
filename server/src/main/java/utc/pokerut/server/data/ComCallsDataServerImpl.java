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
    public Game getGameById(UUID gameID) {
        return dataServerCore.getWaitingGames().stream().filter(game -> game.getId().equals(gameID)).findFirst().get();
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
                if(game.getNbMaxPlayers() == game.getPlayers().size())
                    dataServerCore.getiDataCallsCom().launchGame(game);
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
        game.setStatus(StatusEnum.ON_GOING);

        Collections.shuffle(game.getPlayers()); // mélanger la liste des joueurs

        initRound(game);

        // send next player actions
        List<Action> actions = dataServerCore.getGameEngine().actionCalculation(game.getCurrentRound());
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

        round.getCurrentBets().put(round.getCurrentBettingRound(), action.getBetting());

        //update currentBet
        if (!action.getType().equals(ActionTypeEnum.ALL_IN)){
            round.setCurrentBet(action.getBetting());
        }

        Game game = this.dataServerCore.getOnGoingGame(idGame);
        //check if bettingRound is finished
        // if true
        updateBettingRound(action, round);
        if(isBettingRoundFinished(round)){
            if(round.getCurrentBettingRound() == Round.NB_MAX_BETTING_ROUND) {
                // si la partie est finie
                if(game.getNbRounds() == Game.NB_MAX_ROUND) {
                    // calculer les résultats
                    game.setStatus(StatusEnum.FINISHED);
                    ArrayList<Hand> hands = this.dataServerCore.getGameEngine().getResultsRound(round);
                    round.setHands(hands);
                    ArrayList<Result> rankings = this.dataServerCore.getGameEngine().getRanking(game);
                    this.dataServerCore.getiDataCallsCom().sendUpdateRoundAndEndResults(round, game.getPlayers(), rankings);
                    this.dataServerCore.getOnGoingGames().remove(game); // on enlève la partie de la liste des parties en cours
                } else {
                    // maj la liste des mains avec celles triées par ordre de points
                    ArrayList<Hand> hands = this.dataServerCore.getGameEngine().getResultsRound(round);
                    round.setHands(hands);

                    // envoyer la fin du round au joueur ?

                    // this.dataServerCore.getiDataCallsCom().sendUpdateRound(round, game.getPlayers());
                    // envoyer le nouveau round vérifier si c'est la bonne version
                    initRound(game);
                    this.dataServerCore.getiDataCallsCom().sendNewRound(round, game.getPlayers());
                    List<Action> actions = this.dataServerCore.getGameEngine().actionCalculation(round);
                    this.dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions, round.getCurrentPlayer().getId());
                }

            } else {
                round.setCurrentBettingRound(round.getCurrentBettingRound()+1);
                // la partie n'est pas finie

                //retournage de cartes
                if(round.getCurrentBettingRound() == 2){
                    // on brûle une carte de la pioche
                    round.getCards().removeFirst();
                    // on retourne trois cartes
                    round.getShowedCards().add(round.getCards().removeFirst());
                    round.getShowedCards().add(round.getCards().removeFirst());
                    round.getShowedCards().add(round.getCards().removeFirst());
                } else if(round.getCurrentBettingRound() == 3){
                    // on brûle une carte de la pioche
                    round.getCards().removeFirst();
                    // on retourne trois cartes
                    round.getShowedCards().add(round.getCards().removeFirst());

                } else if(round.getCurrentBettingRound() == 4){
                    // on brûle une carte de la pioche
                    round.getCards().removeFirst();
                    // on retourne trois cartes
                    round.getShowedCards().add(round.getCards().removeFirst());
                }
                this.setNextPlayerRound(round);
                this.dataServerCore.getiDataCallsCom().sendUpdateRound(round, game.getPlayers());

                List<Action> actions = this.dataServerCore.getGameEngine().actionCalculation(round);
                this.dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions, round.getCurrentPlayer().getId());
            }
        } else {

            this.setNextPlayerRound(round);
            this.dataServerCore.getiDataCallsCom().sendUpdateRound(round, game.getPlayers());

            List<Action> actions = this.dataServerCore.getGameEngine().actionCalculation(round);
            this.dataServerCore.getiDataCallsCom().sendNextPlayerActions(actions, round.getCurrentPlayer().getId());
            // envoyer le round
        }

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

    private void initHandList(Game game) {
        ArrayList<Hand> hands = new ArrayList<>();
        Round round = game.getCurrentRound();
        for(Player player : game.getPlayers()) {
            ArrayList<Card> cards = new ArrayList<>();
            for(int i=0; i<Hand.NB_CARDS_IN_HAND; i++) {
                cards.add(round.getCards().removeFirst());
            }
            hands.add(new Hand(player, round, cards));
        }
        round.setHands(hands);
    }

    public void initRound(Game game){
        // init new Round
        Round round = new Round();
        game.getRounds().add(round);
        game.setCurrentRound(round);
        // maj des mains des joueurs
        initHandList(game);
        // set first player
        int noCurrentRound = game.getRounds().size();
        int indexFirstPlayer = (noCurrentRound-1)%game.getPlayers().size();
        round.setHandCurrentPlayer(round.getHands().get(indexFirstPlayer));
        dataServerCore.getiDataCallsCom().sendNewRound(round, game.getPlayers());


        // payer petite blinde
        int smallBlind = Math.round(game.getNbPoints()/100);
        Action actionPayerPetiteBlinde = new Action(ActionTypeEnum.BET, smallBlind, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerPetiteBlinde);

        // payer la grosse blinde
        int bigBlind = smallBlind*2;

        // éventuellment peut poser problème apply action
        //dataServerCore.setNextPlayerRound(game.getPlayers(), game.getCurrentRound());
        game = dataServerCore.getOnGoingGame(game.getId()); // histoire d'être sur que ce soit bien à jour
        setNextPlayerRound(round);
        Action actionPayerGrosseBlinde = new Action(ActionTypeEnum.BET, bigBlind, round.getCurrentPlayer());
        applyAction(round.getCurrentPlayer().getId(), game.getId(), actionPayerGrosseBlinde);

    }

    public void setNextPlayerRound(Round round){
        for(int i=0; i<round.getHands().size(); i++) {
            int indexNextPlayer = (i+1)%round.getHands().size();
            if(round.getHands().get(i).getPlayer().getId() == round.getCurrentPlayer().getId()//
            && !round.getHands().get(indexNextPlayer).getIsFold()) {
                round.setHandCurrentPlayer(round.getHands().get(indexNextPlayer));
                return ;
            }
        }

    }

	@Override
    public void removeUser(UUID playerDisconnectingId) {
        this.dataServerCore.removeConnectedPlayer(playerDisconnectingId);
    }
}