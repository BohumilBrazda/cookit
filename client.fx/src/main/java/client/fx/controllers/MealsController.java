package client.fx.controllers;

import client.fx.config.ClientApplConfig;
import client.repository.model.Author;
import client.repository.model.Meal;
import client.repository.service.remote.rest.AuthorRestService;
import client.repository.service.remote.rest.MealRestService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 27.5.2017.
 */

@FXMLController()
public class MealsController implements Initializable{


    @Autowired
    private MealRestService mealRestService;

    private List<Meal> meals = new ArrayList<>();

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txDescription;

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
    private TableView<Meal> mealsTable;

    @FXML
    private TableColumn<Meal, Long> id;

    @FXML
    private TableColumn<Meal, String> name;

    @FXML
    private TableColumn<Meal, String> description;

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
                new PropertyValueFactory<Meal, Long>("id")
        );
        name.setCellValueFactory(
                new PropertyValueFactory<Meal, String>("name")
        );
        description.setCellValueFactory(
                new PropertyValueFactory<Meal, String>("description")
        );
        meals = mealRestService.findAll();
        ObservableList<Meal> data = FXCollections.observableArrayList(meals);
        mealsTable.setItems(data);
    }

}
