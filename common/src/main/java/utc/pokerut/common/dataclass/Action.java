package utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import static utc.pokerut.common.dataclass.ActionTypeEnum.BET;

public class Action implements Serializable {
    private Player player;
    private ActionTypeEnum type;
    private int betting;
    private Timestamp timestamp;

    public Action (){

    }
    public Action(ActionTypeEnum actionTypeEnum, int littleBlinde, Player player) {
        this.player = player;
        this.type = actionTypeEnum;
        this.betting = littleBlinde;
        this.timestamp = new Timestamp((new Date()).getTime());
    }

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
