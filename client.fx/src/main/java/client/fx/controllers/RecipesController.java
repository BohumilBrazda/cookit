package client.fx.controllers;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
import client.repository.service.remote.rest.RecipeRestService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 9.7.2017.
 *
 * Class is used to control Recipe javaFX client page.
 */
@FXMLController
public class RecipesController implements Initializable {

    @Autowired
    RecipeRestService recipeRestService;

    private Recipe selection;

    private ObservableList<Recipe> recipeTableData;

    private ObservableList<RecipeItem> recipeItemTableData;

    @FXML
    private AnchorPane mealView;

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
    private TableView<Recipe> recipesTable;

    @FXML
    private TableColumn<Recipe, Long> id;

    @FXML
    private TableColumn<Recipe, String> name;

    @FXML
    private TableColumn<Recipe, Long> portions;

    @FXML
    private TableColumn<Recipe, Long> price;

    @FXML
    private TableColumn<Recipe, String> meal;

    @FXML
    private TableColumn<Recipe, String> created;

    @FXML
    private TableColumn<Recipe, String> edited;

    @FXML
    private TableView<RecipeItem> recipeItemsTable;

    @FXML
    private TableColumn<RecipeItem, Long> recipeItemId;

    @FXML
    private TableColumn<RecipeItem, String> recipeItemName;

    @FXML
    private TableColumn<RecipeItem, String> description;

    @FXML
    private TableColumn<RecipeItem, String> ingrediets;

    @FXML
    private TableColumn<RecipeItem, Long> amount;

    @FXML
    private TableColumn<RecipeItem, String> units;

    @FXML
    private Label lblServerStatus;

    @FXML
    void newAction(ActionEvent event) {
        Recipe recipe = new Recipe(txtName.getText());
        Recipe createdRecipe = recipeRestService.create(recipe);
        recipeTableData.add(createdRecipe);
        recipesTable.refresh();
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
        initColumnsData();
        bindRecipeTableColumns();
        createDetailFieldsBinding();
    }

    private void initColumnsData() {
        recipeTableData = FXCollections.observableArrayList(recipeRestService.findAll());
        recipesTable.setItems(recipeTableData);

        recipeItemTableData = FXCollections.observableArrayList(selection.getItems());
    }

    private void bindRecipeTableColumns() {
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

    private void createDetailFieldsBinding() {

        recipesTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Recipe>) c -> {
            if(!c.getList().isEmpty()){
                if(selection != null){
                    txtName.textProperty().unbindBidirectional(selection.nameProperty());
                }
                selection = c.getList().get(0);
                txtName.textProperty().bindBidirectional(selection.nameProperty());
            }
        });

    }
}
