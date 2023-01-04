package utc.pokerut.common.messages;

import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.Player;

import java.util.ArrayList;

public class Init extends Message {
    public ArrayList<Game> games;
    public ArrayList<? extends Player> players;

    public Init(ArrayList<Game> games, ArrayList<? extends Player> players) {
        this.games = games;
        this.players = players;
    }
}
