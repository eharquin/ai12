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

    /**
     *
     */
    public Action () {

    }

    /**
     *
     * @param actionTypeEnum
     * @param littleBlinde
     * @param player
     */
    public Action(ActionTypeEnum actionTypeEnum, int littleBlinde, Player player) {
        this.player = player;
        this.type = actionTypeEnum;
        this.betting = littleBlinde;
        this.timestamp = new Timestamp((new Date()).getTime());
    }

    /**
     *
     * @param actionType
     */
    public Action(ActionTypeEnum actionType) {
        this.type = actionType;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     *
     * @return
     */
    public ActionTypeEnum getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(ActionTypeEnum type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public int getBetting() {
        return betting;
    }

    /**
     *
     * @param betting
     */
    public void setBetting(int betting) {
        this.betting = betting;
    }

    /**
     *
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
