package client.fx.controllers;

import client.repository.model.*;
import client.repository.service.remote.rest.*;
import cz.brazda.cookit.common.Unit;
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
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    AuthorRestService authorRestService;

    @Autowired
    IngredientRestService ingredientRestService;

    @Autowired
    RecipeItemRestService recipeItemRestService;

    @Autowired
    MealRestService mealRestService;

    @Autowired
    UserEventRestService userEventRestService;

    private Recipe selectendRecipe;

    private ObservableList<Recipe> recipeTableData;

    private ObservableList<Author> authors;

    private ObservableList<Meal> meals;

    private ObservableList<Ingredient> ingredients;

    private ObservableList<RecipeItem> recipeItemTableData;

    @FXML
    private AnchorPane mealView;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txDescription;

    @FXML
    private ComboBox<Author> cmbAuthor;

    @FXML
    private ComboBox<Meal> cmbMeal;

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
    private TextField txtRecipeItemName;

    @FXML
    private TextArea txtRecipeItemDescription;

    @FXML
    private ComboBox<Ingredient> cmbIngredient;

    @FXML
    private TextField txtAmount;

    @FXML
    private ComboBox<Unit> cmbUnits;

    @FXML
    private TableView<Recipe> recipesTable;

    @FXML
    private TableView<RecipeItem> recipeItemsTable;

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
    private Button btnAddItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private Button btnRemoveItem;

    @FXML
    void newAction(ActionEvent event) {
        Recipe recipe = new Recipe(txtName.getText());
        Author author = cmbAuthor.getSelectionModel().getSelectedItem();
        Meal meal = cmbMeal.getSelectionModel().getSelectedItem();

        UserEvent creationEvent = new UserEvent();
        creationEvent.setAuthor(author);
        creationEvent.setEventTime(new Date());
        UserEvent savedEvent = userEventRestService.create(creationEvent);


        recipe.setCreated(savedEvent);
        recipe.setEdited(savedEvent);
        recipe.setMeal(meal);
        recipe.setItems(new ArrayList<>());
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

        authors = FXCollections.observableArrayList(authorRestService.findAll());
        cmbAuthor.setItems(authors);

        ingredients = FXCollections.observableArrayList(ingredientRestService.findAll());
        cmbIngredient.setItems(ingredients);

        cmbUnits.setItems(FXCollections.observableArrayList(Unit.values()));

        meals = FXCollections.observableArrayList(mealRestService.findAll());
        cmbMeal.setItems(meals);

        if(selectendRecipe != null){
            recipeItemTableData = FXCollections.observableArrayList(selectendRecipe.getItems());
        }
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
                if(selectendRecipe != null){
                    txtName.textProperty().unbindBidirectional(selectendRecipe.nameProperty());
                }
                selectendRecipe = c.getList().get(0);
                txtName.textProperty().bindBidirectional(selectendRecipe.nameProperty());
            }
        });

    }

    @FXML
    void selectAuthor(ActionEvent event) {

    }

    @FXML
    void selectMeal(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {
        RecipeItem item = new RecipeItem();
        item.setRecipe(selectendRecipe);
        item.setName(txtRecipeItemName.getText());
        item.setDescription(txtRecipeItemDescription.getText());
        item.setAmount(Double.valueOf(txtAmount.getText()));
        item.setUnit((Unit)cmbUnits.getSelectionModel().getSelectedItem());
        item.setIngredient(cmbIngredient.getSelectionModel().getSelectedItem());

        RecipeItem createdRecipeItem = recipeItemRestService.create(item);
        recipeItemTableData.add(createdRecipeItem);
        recipeItemsTable.refresh();
    }

    @FXML
    void updateItem(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

}
