package fr.utc.pokerut.common.dataclass;

import java.util.Date;

public class ServerProfile extends Player{

    private Date birthdate;
    private int gains;
    private int nbGamesPlayed;

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getGains() {
        return gains;
    }

    public void setGains(int gains) {
        this.gains = gains;
    }

    public int getNbGamesPlayed() {
        return nbGamesPlayed;
    }

    public void setNbGamesPlayed(int nbGamesPlayed) {
        this.nbGamesPlayed = nbGamesPlayed;
    }
}
