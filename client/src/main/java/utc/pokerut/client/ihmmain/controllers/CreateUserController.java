package utc.pokerut.client.ihmmain.controllers;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utc.pokerut.client.ihmmain.ViewNames;
import utc.pokerut.common.dataclass.ClientProfile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CreateUserController extends Controller implements Initializable {


    @FXML private TextField name;
    @FXML private TextField surname;
    @FXML private DatePicker birthdate;
    @FXML private TextField pseudo;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordVerification;
    @FXML private Label avatarState;
    private String avatar;
    private ClientProfile updateClientProfile;

    public CreateUserController() {}


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
                avatarState.setText(file.getName());
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
        if ((!name.getText().trim().isEmpty()) && (!surname.getText().trim().isEmpty()) && (!pseudo.getText().trim().isEmpty()) && (birthdate != null) && (!password.getText().trim().isEmpty())  && (!passwordVerification.getText().trim().isEmpty()))
        {

            if(password.getText().equals(passwordVerification.getText()))
            {
                if((password.getText().length() > 6)  && (passwordVerification.getText().length() > 6))
                {
                    core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
                    //Instant instant = Instant.from(birthdate.getValue());
                    try {
                        Refresh();
                        core.getDataInterface().createUser(pseudo.getText(), password.getText(), name.getText(), surname.getText(), java.sql.Date.valueOf(String.valueOf(birthdate)), avatar, null, 0);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Les mot de passe doivent avoir une taille supérieur à 6.");
                    alert.showAndWait();
                }
            }
            else
            {
                //profileState.setText("Choisir mot de passe identique.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Les mot de passe doivent être identique.");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("les champs vides ne sont pas acceptés.");
            alert.showAndWait();
        }
    }

    public void Refresh()
    {
        name.setText("");
        surname.setText("");
        birthdate.setValue(null);
        pseudo.setText("");
        password.setText("");
        passwordVerification.setText("");
        avatarState.setText("");
    }

    public void goBack(ActionEvent actionEvent) {
        Refresh();
        core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(core != null)
        {
            updateClientProfile = core.getDataInterface().getProfile();

            if(updateClientProfile != null)
            {
                name.setText(updateClientProfile.getName());
                surname.setText(updateClientProfile.getSurname());
                birthdate.setUserData(updateClientProfile.getBirthdate());
                pseudo.setText(updateClientProfile.getPseudo());
                password.setText(updateClientProfile.getPassword());
                passwordVerification.setText(updateClientProfile.getPassword());
                avatar = updateClientProfile.getAvatar();
                avatarState.setText("avatar");
            }
        }
    }
}