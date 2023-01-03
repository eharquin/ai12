package utc.pokerut.common.dataclass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ClientProfile extends Player{
    // private String password;
    private String hashedPassword;
    private String name;
    private String surname;
    private Date birthdate;
    private int gains;
    private int nbGamesPlayed;
    private String ip;
    private int port;
    private ArrayList<Game> savedGames;

    public ClientProfile(UUID id, String pseudo, String avatar, String password, String name, String surname, Date birthdate, String ip, int port) {
        super(id, pseudo, avatar);

        // TODO : test le password hash
        // this.password = password;
        this.hashedPassword = hashPassword(password);
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gains = 0;
        this.nbGamesPlayed = 0;
        this.ip = ip;
        this.port = port;
        this.savedGames = null;
    }

    public String hashPassword(String password) {
        /*
         * Method that receives a password in string
         * and returns the password heshed using MD5 algorithm.
         */
        String hashed_password = null;

        try
        {
            // Creation d'une instance MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // on y ajoute les bytes du mot de passe
            md.update(password.getBytes());
            // on hash les bytes
            byte[] hash_bytes = md.digest();
            // conversion au hexadecimal
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash_bytes.length; i++)
            {
                sb.append(Integer.toString((hash_bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // on obtient le mot de passe hashed
            hashed_password = sb.toString();
        } catch (NoSuchAlgorithmException e_algo)
        {
            e_algo.printStackTrace();
        }

        return hashed_password;
    }

    public String getPassword() {
        return hashedPassword;
    }

    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    public void setSavedGames(ArrayList<Game> savedGames) {
        this.savedGames = savedGames;
    }

}



