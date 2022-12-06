package utc.pokerut.common.dataclass;

public enum StatusEnum {
    WAITING_FOR_PLAYER("En attente"),
    ON_GOING("En cours"),
    FINISHED("Terminé");
    private final String name;

    private StatusEnum(String s) {
        name = s;
    }


    public String toString() {
        return this.name;
    }
}
