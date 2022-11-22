package fr.utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.security.Timestamp;

public class Action implements Serializable {
    private Player player;
    private ActionTypeEnum type;
    private int betting;
    private Timestamp timestamp;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ActionTypeEnum getType() {
        return type;
    }

    public void setType(ActionTypeEnum type) {
        this.type = type;
    }

    public int getBetting() {
        return betting;
    }

    public void setBetting(int betting) {
        this.betting = betting;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
