package utc.pokerut.common.dataclass;

import java.io.Serializable;

public class Card  implements Serializable {

    private String symbol;
    private int value;

    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value  = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
