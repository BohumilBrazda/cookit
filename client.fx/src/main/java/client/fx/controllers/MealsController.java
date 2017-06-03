package client.fx.controllers;

import client.repository.model.Author;
import client.repository.model.Meal;
import client.repository.service.remote.rest.MealRestService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 27.5.2017.
 */

@FXMLController()
public class MealsController implements Initializable{


    @Autowired
    private MealRestService mealRestService;

    private Meal selection;

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

    private ObservableList<Meal> tableData;

    @FXML
    void newAction(ActionEvent event) {
        Meal meal = new Meal(txtName.getText(), txDescription.getText());
        Meal createdMeal = mealRestService.create(meal);
        tableData.add(createdMeal);
        mealsTable.refresh();
    }

    @FXML
    void removeAction(ActionEvent event) {
        if(selection != null){
            mealRestService.delete(selection.getId());
            tableData.remove(selection);
            mealsTable.refresh();
        }
    }

    @FXML
    void searchAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {
        Meal mealToUpdate = mealsTable.getSelectionModel().getSelectedItem();
        mealRestService.update(mealToUpdate);
        mealsTable.refresh();
    }
    private void initColumnsData() {
        tableData = FXCollections.observableArrayList(mealRestService.findAll());
        mealsTable.setItems(tableData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnsData();
        bindTableColumns();
        createDetailFieldsBinding();
    }

    private void createDetailFieldsBinding() {

        mealsTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Meal>) c -> {
            if(!c.getList().isEmpty()){
                if(selection != null){
                    txtName.textProperty().unbindBidirectional(selection.nameProperty());
                    txDescription.textProperty().unbindBidirectional(selection.descriptionProperty());
                }
                selection = c.getList().get(0);
                txtName.textProperty().bindBidirectional(selection.nameProperty());
                txDescription.textProperty().bindBidirectional(selection.descriptionProperty());
            }
        });

    }

    private void bindTableColumns() {
        id.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        name.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        description.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );
    }

}
