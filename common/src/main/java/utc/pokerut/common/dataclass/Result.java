package utc.pokerut.common.dataclass;

public class Result {
    private int rank;
    private int nbPoints;

    private Player player;

    public Result(){

    }
    public Result(Player player, int points) {
        this.player = player;
        this.nbPoints = points;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
