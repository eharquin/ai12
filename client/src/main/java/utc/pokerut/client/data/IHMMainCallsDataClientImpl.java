package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.UUID;

public class IHMMainCallsDataClientImpl implements IHMMainCallsData {
    private Core myDataCore;

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
        //il faudra une méthode pour ouvrir le fichier et renvoie une exception si aucun correspondant.Sinon renvoie le clientProfile
        myDataCore.setProfile(checkAuthentication(login, password));
        //Crado à modifier:

        if (myDataCore.getProfile().getIp() == null){
            ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());
            myDataCore.getiDataCallsCom().Connection(myNewUser, myDataCore.getProfile().getIp(), myDataCore.getProfile().getPort());
        }
        else{
            ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());
            myDataCore.getiDataCallsCom().Connection(myNewUser, ip, port);
           }
    }

    public ClientProfile checkAuthentication(String login, String password) throws Exception {
        // get Profiles directory, it must be created at:
        //  > 'client/src/main/java/utc/pokerut/client/data/profiles'
        File folder = new File("profiles/");
        File[] listOfFiles = folder.listFiles();
        ObjectInputStream ois = null;

        for (int i = 0; i < listOfFiles.length; i++) {
            // on verifie que c'est un fichier
            if (listOfFiles[i].isFile()) {
                // on fait la lecture se l'objet serializable
                try {
                    // ouverture du fichier
                    FileInputStream file = new FileInputStream(listOfFiles[i].getName());
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


    @Override
    public void createUser(String pseudo, String password, String name, String surname, Date birthdate, String avatar, String ip, int port){
        UUID userUUID = UUID.randomUUID();
        ClientProfile myUser = new ClientProfile(userUUID, pseudo, avatar, password, name, surname, birthdate, ip, port);
        //truc crado qu'on fait pour aller plus vite à enlever pour la V2
        myDataCore.setProfile(myUser);


        //appel de la méthode saveProfil
        //saveProfile(myUser);


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
