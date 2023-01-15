package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.*;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class IHMMainCallsDataClientImpl implements IHMMainCallsData {

    private Core myDataCore;
    private final String PROFILE_DIRECTORY_NAME ="profiles";

    /**
     *
     * @param myDataCore
     */
    public IHMMainCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

    /**
     *
     * @param newGame
     */
    @Override
    public void displayGame(Game newGame) {

    }

    /**
     *
     * @param gameNewPlayer
     * @param newPlayer
     * @param idUser
     */
    @Override
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser) {

    }

    /**
     *
     * @param login
     * @param password
     * @param ip
     * @param port
     * @throws Exception
     */
    @Override
    public void login(String login, String password, String ip, int port) throws Exception {
        myDataCore.setProfile(checkAuthentication(login, password));
        System.out.println(ip + ":" + port);
        ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());

        if (myDataCore.getProfile().getIp() != null) {
            myDataCore.getiDataCallsCom().connectionUser(myNewUser, myDataCore.getProfile().getIp(), myDataCore.getProfile().getPort());
        }
        else {
            myDataCore.getiDataCallsCom().connectionUser(myNewUser, ip, port);
        }
    }

    /**
     *
     * @param login
     * @param password
     * @return
     * @throws Exception
     */
    public ClientProfile checkAuthentication(String login, String password) throws Exception {
        /*  get Profiles directory, it must be created at:
         *  > 'client/src/main/java/utc/pokerut/client/data/profiles'
         */

        File directory = new File(PROFILE_DIRECTORY_NAME);
        // if the folder doesn't exist
        if (!directory.exists()) {
            // do something
            directory.mkdir();
        }
        File[] listOfFiles = directory.listFiles();
        ObjectInputStream ois = null;

        for (int i = 0; i < listOfFiles.length; i++) {
            // verify it is a file
            if (listOfFiles[i].isFile()) {
                // read the serializable object
                try {
                    // open the file
                    FileInputStream file = new FileInputStream(listOfFiles[i]);
                    ois = new ObjectInputStream(file);
                    // read the object
                    ClientProfile profile = (ClientProfile) ois.readObject();
                    if (profile.getPseudo().equals(login) && profile.getPassword().equals(profile.hashPassword(password))) {
                        // returns the corresponding profile
                        return profile;
                    }
                } catch (IOException e_read) {
                    // error during the file opening or reading
                    // e_read.printStackTrace();
                    if (i >= listOfFiles.length-1) {
                        throw new Exception("Fichier non trouvé");
                    }
                } catch(ClassNotFoundException e_class) {
                    // the object is not compatible with the class type
                    throw new Exception("Erreur");
                } finally {
                    try {
                        // close the pipeline
                        if (ois != null) {
                            ois.close();
                        }
                    } catch (IOException e_close) {
                        // error during the pipeline closure
                        throw new Exception(e_close);
                    }
                }
            }
        }
        throw new Exception("Aucun profil trouvé");
    }

    /**
     *
     * @param profile
     * @throws Exception
     */
    public void saveProfile(ClientProfile profile) throws Exception {

        String file_path =  PROFILE_DIRECTORY_NAME +"/"+profile.getId().toString() + ".ser";
        ObjectOutputStream oos = null;

        try {
            File directory = new File(PROFILE_DIRECTORY_NAME);
            // if the repertory doesn't exist
            if(!directory.exists()) {
                // do something
                directory.mkdir();
            }
            // open the file
            FileOutputStream file = new FileOutputStream(file_path);
            // create an ObjectStream instance with the opened file
            oos = new ObjectOutputStream(file);
            // save the profile in the file
            oos.writeObject(profile);
            // push the binaries through the pipeline
            oos.flush();
        } catch (IOException e_write) {
            // error during the opening or save
            throw new Exception(e_write);
        } finally {
            try {
                // close the pipeline
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e_close) {
                // error during the pipeline closure
                throw new Exception(e_close);
            }
        }
    }

    /**
     *
     * @param pseudo
     * @param password
     * @param name
     * @param surname
     * @param birthdate
     * @param avatar
     * @param ip
     * @param port
     * @throws Exception
     */
    @Override
    public void createUser(String pseudo, String password, String name, String surname, Date birthdate, String avatar, String ip, int port) throws Exception {
        UUID userUUID = UUID.randomUUID();
        ClientProfile myUser = new ClientProfile(userUUID, pseudo, avatar, password, name, surname, birthdate, ip, port);
        myDataCore.setProfile(myUser);
        saveProfile(myUser);
    }

    /**
     *
     * @param name
     * @param minimalBet
     * @param nbMaxPlayers
     * @param nbRounds
     * @param nbPoints
     * @throws Exception
     */
    @Override
    public void createGame(String name, int minimalBet, int nbMaxPlayers, int nbRounds, int nbPoints) throws Exception {
        Game game = new Game(name, nbMaxPlayers, nbPoints, minimalBet, nbRounds);
        game.setCreator(new Player(getProfile()));
        game.getPlayers().add(game.getCreator());
        game.setStatus(StatusEnum.WAITING_FOR_PLAYER);
        myDataCore.setCurrentGame(game);
        myDataCore.getiDataCallsCom().initGameClient(game);
    }

    /**
     *
     * @param idGame
     * @param idUser
     */
    public void askJoinTableMainComCli(UUID idGame, UUID idUser) {
        myDataCore.getiDataCallsCom().askJoinTableMainComCli(idGame, idUser);
    }

    /**
     *
     * @param PCLGame
     */
    @Override
    public void setPCLGame(PropertyChangeListener PCLGame){
        myDataCore.addPropertyChangeListenerGame(PCLGame);
    }

    /**
     *
     * @param PCLPlayer
     */
    @Override
    public void setPCLPlayer(PropertyChangeListener PCLPlayer) {
        myDataCore.addPropertyChangeListenerPlayer(PCLPlayer);
    }

    /**
     *
     * @return
     */
    @Override
    public ClientProfile getProfile() {
        return this.myDataCore.getProfile();
    }

    /**
     *
     */
    @Override
    public void logout(){
        myDataCore.getiDataCallsCom().logoutUser(myDataCore.getProfile().getId());
    }
}
