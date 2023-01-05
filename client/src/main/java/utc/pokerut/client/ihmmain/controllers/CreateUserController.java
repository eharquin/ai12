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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

    private void ChooseAvatar(ActionEvent event, String initDir) throws IOException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(initDir));
        File selectedFile = fileChooser.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Photos", "*.png", "*.jpg")
        );

        if( selectedFile != null) {
            {
                byte[] byteAvatar = Files.readAllBytes(selectedFile.toPath());
                avatar = Base64.getEncoder().encodeToString(byteAvatar);
            }
       }
    }

    @FXML
    protected void AddAvatar(ActionEvent e) throws IOException {
        ChooseAvatar(e, ".");
    }

    @FXML
    protected void AddBaseAvatar(ActionEvent e) throws IOException {
        ChooseAvatar(e,System.getProperty("user.dir") + "/client/src/main/resources/utc/pokerut/client/img");
    }

    @FXML
    protected void CreatedProfile(){
        if ((!name.getText().trim().isEmpty()) && (!surname.getText().trim().isEmpty()) && (!pseudo.getText().trim().isEmpty()) && (birthdate != null) && (password.getText().length() > 6)  && (passwordVerification.getText().length() > 6))
        {
            if(password.getText().equals(passwordVerification.getText()))
            {
                core.getMainController().Navigate(ViewNames.LOGIN_VIEW);
                try {
                    core.getDataInterface().createUser(pseudo.getText(), password.getText(), name.getText(), surname.getText(), new Date(), avatar, null, 0);
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