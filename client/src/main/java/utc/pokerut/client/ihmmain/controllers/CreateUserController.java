package utc.pokerut.client.ihmmain.controllers;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.ClientProfile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateUserController extends Controller {


    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private DatePicker birthdate;
    @FXML private TextField pseudo;
    @FXML private TextField password;
    @FXML private TextField passwordVerification;
    @FXML private Label profileState;
    @FXML private Label avatarState;
    private String avatar;
    private ClientProfile updateClientProfile;

    public CreateUserController()
    {
        ClientProfile client =
        if ((newUser == false) & (updateUser != null))
        {
            updateClientProfile = updateUser;
        }
        else if (((newUser == true) & (updateUser != null)) | ((newUser == false) & (updateUser == null)))
        {
            //error
        }
        else
        {
            updateClientProfile = null;
        }
    }


    private void ChooseAvatar(String path) throws IOException
    {
        JFileChooser dialogue = new JFileChooser(new File(path));

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "png");
        dialogue.setFileFilter(filter);
        File file;

        if (dialogue.showOpenDialog(null)==
                JFileChooser.APPROVE_OPTION) {
            file = dialogue.getSelectedFile();
            if( file != null)
            {
                byte[] byteAvatar = Files.readAllBytes(file.toPath());
                avatar = Base64.getEncoder().encodeToString(byteAvatar);
            }
        }
    }

    @FXML
    protected void AddAvatar() throws IOException {
        ChooseAvatar(".");
    }

    @FXML
    protected void AddBaseAvatar() throws IOException {
        ChooseAvatar(System.getProperty("user.dir") + "/client/src/main/resources/utc/pokerut/client/img");
    }

    @FXML
    protected void CreatedProfile(){
        if ((!name.getText().trim().isEmpty()) && (!surname.getText().trim().isEmpty()) && (!pseudo.getText().trim().isEmpty()) && (birthdate != null) && (password.getText().length() > 6)  && (passwordVerification.getText().length() > 6))
        {
            if(password.getText().equals(passwordVerification.getText()))
            {
                /*ClientProfile profile = new ClientProfile();
                profile.setName(name.getText());
                profile.setPassword(password.getText());
                profile.setSurname(surname.getText());
                profile.setBirthdate(java.sql.Date.valueOf(String.valueOf(birthdate)));
                profile.setGains(0);
                profile.setIp(null);
                profile.setNbGamesPlayed(0);
                profile.setId(null);
                profile.setPseudo(pseudo.getText());
                profile.setPort(null);
                profile.setAvatar(null);
            //sendUserData(profile);
            //Todo : besoin constructeur par defaut pour la classe ClientProfile*/
                core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
                //Instant instant = Instant.from(birthdate.getValue());
                try {
                    core.getDataInterface().createUser(pseudo.getText(), password.getText(), name.getText(), surname.getText(), java.sql.Date.valueOf(String.valueOf(birthdate)), avatar, null, 0);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                profileState.setText("Choisir mot de passe identique.");
            }
        }
        else
        {
            profileState.setText("Remplir tous les champs.");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
    }
}