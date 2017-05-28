package client.fx.controllers;

import client.repository.model.Author;
import client.repository.service.remote.rest.AuthorRestService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 27.5.2017.
 */
@FXMLController()
public class AuthorsController implements Initializable{


    @Autowired
    private AuthorRestService authorRestService;

    private List<Author> authors;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtSecondName;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnNew;

    @FXML
    private TableView<Author> authorsTable;

    @FXML
    private TableColumn<Author, Long> id;

    @FXML
    private TableColumn<Author, String> firstName;

    @FXML
    private TableColumn<Author, String> secondName;

    @FXML
    private Label lblServerStatus;

    @FXML
    void newAction(ActionEvent event) {

    }

    @FXML
    void removeAction(ActionEvent event) {

    }

    @FXML
    void searchAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(
                new PropertyValueFactory<Author, Long>("id")
        );
        firstName.setCellValueFactory(
                new PropertyValueFactory<Author, String>("firstName")
        );
        secondName.setCellValueFactory(
                new PropertyValueFactory<Author, String>("secondName")
        );

        authors = authorRestService.findAll();
        ObservableList<Author> data = FXCollections.observableArrayList(authors);
        authorsTable.setItems(data);
    }
}
