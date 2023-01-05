package utc.pokerut.client.ihmmain.controllers;


import java.io.IOException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    public void initialize() {}
    @FXML 
    void handleCreateGameButtonAction(ActionEvent event) {
        if (tablename.getText().length()<=0 ||miseMinimale.getText().length()<=0 || nbJoueurs.getText().length()<=0 || maxTours.getText().length()<=0 || creditsDepart.getText().length()<=0  ){
            errorLengthMsg.setVisible(true);
        }   
        else  if(!miseMinimale.getText().matches("^(0*[1-9][0-9]*(\\.[0-9]+)?|0+\\.[0-9]*[1-9][0-9]*)") || !nbJoueurs.getText().matches(format) || !maxTours.getText().matches(format) || !creditsDepart.getText().matches(format)){
            errorValuesMsg.setVisible(true);
        }    else {
            errorLengthMsg.setVisible(false);
            errorValuesMsg.setVisible(false);
            try {
                core.getDataInterface().createGame(tablename.getText(), Integer.parseInt(miseMinimale.getText()), Integer.parseInt(nbJoueurs.getText()), Integer.parseInt(maxTours.getText()), Integer.parseInt(creditsDepart.getText()));
                core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
            } catch (Exception e) {
                System.out.println("Can't create the game");
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    void handleCancelButtonAction(ActionEvent event){
        core.getMainController().Navigate(ViewNames.GAME_LIST_VIEW);
    }
}
