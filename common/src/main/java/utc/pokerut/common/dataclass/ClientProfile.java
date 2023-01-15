package utc.pokerut.common.dataclass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ClientProfile extends Player {

    private String hashedPassword;
    private String name;
    private String surname;
    private Date birthdate;
    private int gains;
    private int nbGamesPlayed;
    private String ip;
    private int port;
    private ArrayList<Game> savedGames;

    /**
     *
     * @param id
     * @param pseudo
     * @param avatar
     * @param password
     * @param name
     * @param surname
     * @param birthdate
     * @param ip
     * @param port
     */
    public ClientProfile(UUID id, String pseudo, String avatar, String password, String name, String surname, Date birthdate, String ip, int port) {
        super(id, pseudo, avatar);

        // TODO : test le password hash
        // this.password = password;
        this.hashedPassword = hashPassword(password);
        this.name           = name;
        this.surname        = surname;
        this.birthdate      = birthdate;
        this.gains          = 0;
        this.nbGamesPlayed  = 0;
        this.ip             = ip;
        this.port           = port;
        this.savedGames     = null;
    }

    /**
     * Returns the password hashed using MD5 algorithm.
     * @param password
     * @return
     */
    public String hashPassword(String password) {
        String hashed_password = null;

        try {
            // create a MD5 instance
            MessageDigest md = MessageDigest.getInstance("MD5");
            // add the password bytes
            md.update(password.getBytes());
            // hash the bytes
            byte[] hash_bytes = md.digest();
            // convert in hexadecimal
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash_bytes.length; i++) {
                sb.append(Integer.toString((hash_bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // hashed password obtained
            hashed_password = sb.toString();
        } catch (NoSuchAlgorithmException e_algo) {
            e_algo.printStackTrace();
        }
        return hashed_password;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return hashedPassword;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.hashedPassword = hashPassword(password);
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @param birthdate
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     *
     * @return
     */
    public int getGains() {
        return gains;
    }

    /**
     *
     * @param gains
     */
    public void setGains(int gains) {
        this.gains = gains;
    }

    /**
     *
     * @return
     */
    public int getNbGamesPlayed() {
        return nbGamesPlayed;
    }

    /**
     *
     * @param nbGamesPlayed
     */
    public void setNbGamesPlayed(int nbGamesPlayed) {
        this.nbGamesPlayed = nbGamesPlayed;
    }

    /**
     *
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     *
     * @return
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     *
     * @return
     */
    public ArrayList<Game> getSavedGames() {
        return savedGames;
    }

    /**
     *
     * @param savedGames
     */
    public void setSavedGames(ArrayList<Game> savedGames) {
        this.savedGames = savedGames;
    }

}



