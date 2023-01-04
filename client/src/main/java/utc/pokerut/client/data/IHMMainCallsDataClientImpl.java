package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Date;
import java.util.UUID;

public class IHMMainCallsDataClientImpl implements IHMMainCallsData {
    private Core myDataCore;
    private final String PROFILE_DIRECTORY_NAME ="profiles";

    public IHMMainCallsDataClientImpl(Core myDataCore) {
        this.myDataCore = myDataCore;
    }

    @Override
    public void displayGame(Game newGame) {

    }

    @Override
    public void addUserToGameDataMainClient(Game gameNewPlayer, ServerProfile newPlayer, UUID idUser) {

    }

    @Override
    public void login(String login, String password, String ip, int port) throws Exception {
        myDataCore.setProfile(checkAuthentication(login, password));

        System.out.println(ip + ":" + port);

        ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());
        if (myDataCore.getProfile().getIp() != null){
            myDataCore.getiDataCallsCom().connectionUser(myNewUser, myDataCore.getProfile().getIp(), myDataCore.getProfile().getPort());
        }
        else{
            myDataCore.getiDataCallsCom().connectionUser(myNewUser, ip, port);
        }
    }

    public ClientProfile checkAuthentication(String login, String password) throws Exception {
        // get Profiles directory, it must be created at:
        //  > 'client/src/main/java/utc/pokerut/client/data/profiles'
        File directory = new File(PROFILE_DIRECTORY_NAME);
        // si le répertoire n'existe pas
        if(!directory.exists() ) {
            // do something
            directory.mkdir();
        }
        File[] listOfFiles = directory.listFiles();
        ObjectInputStream ois = null;

        for (int i = 0; i < listOfFiles.length; i++) {
            // on verifie que c'est un fichier
            if (listOfFiles[i].isFile()) {
                // on fait la lecture se l'objet serializable
                try {
                    // ouverture du fichier
                    FileInputStream file = new FileInputStream(listOfFiles[i]);
                    ois = new ObjectInputStream(file);
                    // lecture de l'objet
                    ClientProfile profile = (ClientProfile) ois.readObject();
                    if (profile.getPseudo().equals(login) && profile.getPassword().equals(password)) {
                        // retour du profil correspondant
                        return profile;
                    }
                } catch (IOException e_read) {
                    // erreur rencontre pendant l'ouverture et/ou lecture
                    // e_read.printStackTrace();
                    throw new Exception("Fichier non trouvé");
                } catch(ClassNotFoundException e_class) {
                    // l'objet obtenu ne correspond pas au type de la classe
                    throw new Exception("Erreur");
                } finally {
                    try {
                        // fermeture du pipeline
                        if (ois != null) {
                            ois.close();
                        }
                    } catch (IOException e_close) {
                        // erreur rencontre pendant la fermeture du pipeline
                        throw new Exception(e_close);
                    }
                }

            }
        }
        throw new Exception("Aucun profil trouvé");
    }

    public void saveProfile(ClientProfile profile) throws Exception {

        String file_path =  PROFILE_DIRECTORY_NAME +"/"+profile.getId().toString() + ".ser";
        ObjectOutputStream oos = null;

        try {
            File directory = new File(PROFILE_DIRECTORY_NAME);
            // si le répertoire n'existe pas
            if(!directory.exists() ) {
                // do something
                directory.mkdir();
            }
            // on ouvre le fichier
            FileOutputStream file = new FileOutputStream(file_path);
            // on cree une instance d'un ObjectStream en utilisant le fichier ouvert
            oos = new ObjectOutputStream(file);
            // on sauvegarde le profile dans le fichier
            oos.writeObject(profile);
            // on push les binaires par le pipeline
            oos.flush();
        } catch (IOException e_write) {
            // erreur rencontre pendant l'ouverture et/ou sauvegarde
            throw new Exception(e_write);
        } finally {
            try {
                // fermeture du pipeline
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e_close) {
                // erreur rencontre pendant la fermeture du pipeline
                throw new Exception(e_close);
            }
        }
    }
    @Override
    public void createUser(String pseudo, String password, String name, String surname, Date birthdate, String avatar, String ip, int port) throws Exception {
        UUID userUUID = UUID.randomUUID();
        ClientProfile myUser = new ClientProfile(userUUID, pseudo, avatar, password, name, surname, birthdate, ip, port);
        //truc crado qu'on fait pour aller plus vite à enlever pour la V2
        myDataCore.setProfile(myUser);


        //appel de la méthode saveProfil
        saveProfile(myUser);


    }

    @Override
    public void askJoinTableMainComCli(UUID idGame, UUID idUser) {
        myDataCore.getiDataCallsCom().askJoinTableMainComCli(idGame, idUser);
    }

    @Override
    public void setPCLGame(PropertyChangeListener PCLGame){
        myDataCore.addPropertyChangeListenerGame(PCLGame);
    }


    @Override
    public void setPCLPlayer(PropertyChangeListener PCLPlayer){
        myDataCore.addPropertyChangeListenerPlayer(PCLPlayer);
    }
}
