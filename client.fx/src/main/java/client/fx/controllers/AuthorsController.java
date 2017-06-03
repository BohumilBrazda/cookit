package client.fx.controllers;

import client.repository.model.Author;
import client.repository.service.remote.rest.AuthorRestService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 27.5.2017.
 */
@FXMLController()
public class AuthorsController implements Initializable{


    @Autowired
    private AuthorRestService authorRestService;

    private Author selection;

    private ObservableList<Author> tableData;

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
    private Button btnCreate;

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
    void createAction(ActionEvent event) {
        Author author = new Author(txtFirstName.getText(), txtSecondName.getText());
        Author createdAuthor = authorRestService.create(author);
        tableData.add(createdAuthor);
        authorsTable.refresh();
    }

    @FXML
    void removeAction(ActionEvent event) {
        if(selection != null){
            authorRestService.delete(selection.getId());
            tableData.remove(selection);
        }
    }

    @FXML
    void searchAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {
        Author authorToUpdate = authorsTable.getSelectionModel().getSelectedItem();
        authorRestService.update(authorToUpdate);
        authorsTable.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindTableColumns();
        initColumnsData();
        createDetailFieldsBinding();

    }

    private void initColumnsData() {
        tableData = FXCollections.observableArrayList(authorRestService.findAll());
        authorsTable.setItems(tableData);
    }

    private void bindTableColumns() {
        id.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        firstName.setCellValueFactory(
                new PropertyValueFactory<>("firstName")
        );
        secondName.setCellValueFactory(
                new PropertyValueFactory<>("secondName")
        );
    }

    private void createDetailFieldsBinding() {
        authorsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Author>) c -> {
            if(!c.getList().isEmpty()){
                if(selection != null){
                    txtFirstName.textProperty().unbindBidirectional(selection.firstNameProperty());
                    txtSecondName.textProperty().unbindBidirectional(selection.secondNameProperty());
                }
                selection = c.getList().get(0);
                txtFirstName.textProperty().bindBidirectional(selection.firstNameProperty());
                txtSecondName.textProperty().bindBidirectional(selection.secondNameProperty());

            }
        });

    }
}
