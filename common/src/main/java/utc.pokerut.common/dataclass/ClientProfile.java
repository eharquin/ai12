package utc.pokerut.common.dataclass;

import java.util.ArrayList;
import java.util.Date;

public class ClientProfile extends Player{
    private String password;
    private String name;
    private String surname;
    private Date birthdate;
    private int gains;
    private int nbGamesPlayed;
    private String ip;
    private String port;
    private ArrayList<Game> savedGames;

    public ClientProfile() {}
    public ClientProfile(ClientProfile clientProfile) {
        super(clientProfile);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(ArrayList<Game> savedGames) {
        this.savedGames = savedGames;
    }

}
