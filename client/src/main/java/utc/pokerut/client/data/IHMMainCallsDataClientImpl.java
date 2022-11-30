package utc.pokerut.client.data;

import utc.pokerut.common.dataclass.ClientProfile;
import utc.pokerut.common.dataclass.Game;
import utc.pokerut.common.dataclass.ServerProfile;
import utc.pokerut.common.interfaces.client.IHMMainCallsData;

import java.beans.PropertyChangeListener;
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
        //myDataCore.setClientProfile(checkAuthentification(login, password));
        //Crado à modifier:
        if (login.equals(this.myDataCore.getProfile().getPseudo()) && password.equals(this.myDataCore.getProfile().getPassword())){
            if (ip == null){
                ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());
                myDataCore.getiDataCallsCom().Connection(myNewUser, myDataCore.getProfile().getIp(), myDataCore.getProfile().getPort());
            }
            else{
                ServerProfile myNewUser = new ServerProfile(myDataCore.getProfile());
                myDataCore.getiDataCallsCom().Connection(myNewUser, ip, port);
               }
        }
        else{
            //créer les exceptions qu'on renvoie dans des classes
            throw new Exception("Nom d'utilisateur ou mot de passe incorrect");
        }


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
