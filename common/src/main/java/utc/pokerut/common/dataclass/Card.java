package utc.pokerut.common.dataclass;

import java.io.Serializable;

public class Card  implements Serializable {

    private String symbol;
    private int value;

    /**
     *
     * @param symbol
     * @param value
     */
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value  = value;
    }

    /**
     *
     * @return
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     *
     * @param symbol
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     *
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

}
