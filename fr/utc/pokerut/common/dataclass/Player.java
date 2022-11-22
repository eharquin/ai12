package fr.utc.pokerut.common.dataclass;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {
    private UUID id;
    private String pseudo;
    private String avatar;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
