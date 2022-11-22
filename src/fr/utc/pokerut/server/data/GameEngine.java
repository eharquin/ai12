package src.fr.utc.pokerut.server.data;

import src.fr.utc.pokerut.common.dataclass.Game;
import src.fr.utc.pokerut.common.dataclass.Result;

import java.util.ArrayList;

public class GameEngine {
    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Result> getRanking() {
        ArrayList<Result> results = new ArrayList<>();
        return results;
    }

    public boolean isBettingPossible() {
        return true;
    }

    public boolean isAllInPossible(){
        return true;
    }

    public boolean isCheckingPossible(){
        return true;
    }

    public boolean isRaisingPossible(){
        return true;
    }

    public boolean isCallingPossible(){
        return true;
    }
}
