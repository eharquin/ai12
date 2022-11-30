package utc.pokerut.client.ihmmain.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
//import utc.pokerut.common.dataclass.Game;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import utc.pokerut.common.dataclass.Game;


public class TablesViewController {
    @FXML
    private TableView<Game> myTableView;

    @FXML
    private TableColumn<Game, String> name;

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));

        myTableView.getItems().setAll(getItemsToAdd());
    }

    private List<Game> getItemsToAdd(){
        List<Game> list = new ArrayList<>();
        Game game1 = new Game();
        game1.setName("Bob");
        list.add(game1);
        System.out.println(game1.getName());
        return list;
    }

    
}