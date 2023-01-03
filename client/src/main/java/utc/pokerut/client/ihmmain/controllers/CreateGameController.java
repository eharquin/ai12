package utc.pokerut.client.ihmmain.controllers;


import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utc.pokerut.client.ihmmain.ViewNames;

import java.util.regex.*;

public class CreateGameController extends Controller {
    private String format = "^[1-9][0-9]*";
    //private String format = "^[1-9][0-9]*";

    //private String format = "^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)";
    //private String format = "d+(\\.\\d+)?";

    @FXML
    private TextField tablename;

    @FXML
    private TextField miseMinimale;

    @FXML
    private TextField nbJoueurs;

    @FXML
    private TextField maxTours;

    @FXML
    private TextField creditsDepart;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createGameButton;

    @FXML
    private Label errorValuesMsg;

    @FXML
    private Label errorLengthMsg;

    @FXML
    private Label errorNbJoueurs;

    public void initialize() {}
    @FXML 
    void handleCreateGameButtonAction(ActionEvent event) {
        if (tablename.getText().length()<=0 ||miseMinimale.getText().length()<=0 || nbJoueurs.getText().length()<=0 || maxTours.getText().length()<=0 || creditsDepart.getText().length()<=0  ){
           showAlertWithoutHeaderText(3);
            //errorLengthMsg.setVisible(true);
        }   
        else  if(!miseMinimale.getText().matches("^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)") || !maxTours.getText().matches(format) || !creditsDepart.getText().matches(format)){
            showAlertWithoutHeaderText(2);
            //errorValuesMsg.setVisible(true);
        }
        else if(!nbJoueurs.getText().matches("^[2-9][0-9]*")){
            showAlertWithoutHeaderText(1);
            //errorNbJoueurs.setVisible(true);
            
        }    else {
            //errorLengthMsg.setVisible(false);
            //errorValuesMsg.setVisible(false);
            //errorNbJoueurs.setVisible(false);
            //createGame();
        }
    }
    
    private void showAlertWithoutHeaderText(int type) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning alert");
        // Header Text: null
        alert.setHeaderText(null);
        switch(type){
            case 1:
                alert.setContentText("Nombre de joueurs insuffisant !");
                break;
            case 2:
                alert.setContentText("Valeurs numériques requises !");
                break;
            case 3:
                alert.setContentText("Remplissez tous les champs !");
        }
        alert.showAndWait();
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event){
        core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
    }
    
}
