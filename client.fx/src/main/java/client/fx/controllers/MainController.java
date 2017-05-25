package client.fx.controllers;


import client.repository.model.Author;
import client.repository.service.remote.rest.AuthorRestService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class MainController implements Initializable{

    @Autowired
    private AuthorRestService authorRestService;

    private List<Author> authors;

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
    private TableColumn<Author, String> firstname;

    @FXML
    private TableColumn<Author, String> secondname;

    @FXML
    private Pane pnlServerStatus;
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
        int row = authorsTable.getSelectionModel().getFocusedIndex();
        Author a = authors.get(row);
        a.setSecondName(a.getSecondName() + " aa");
        authorRestService.update(a);
        authorsTable.refresh();
    }

    @FXML
    void updateAction(ActionEvent event) {
        Author newAuthor = new Author();
        newAuthor.setFirstName("Petr");
        newAuthor.setSecondName("Petrovic");
        Author savedAuthor = authorRestService.create(newAuthor);
        authors.add(savedAuthor);
        ObservableList<Author> data = FXCollections.observableArrayList(authors);
        authorsTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(
                new PropertyValueFactory("id")
        );
        firstname.setCellValueFactory(
                new PropertyValueFactory("firstName")
        );
        secondname.setCellValueFactory(
                new PropertyValueFactory("secondName")
        );

        authors = authorRestService.findAll();
        ObservableList<Author> data = FXCollections.observableArrayList(authors);
        authorsTable.setItems(data);
    }
}

