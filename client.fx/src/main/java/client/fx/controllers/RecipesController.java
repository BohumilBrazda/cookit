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
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Bohumil Brázda on 9.7.2017.
 * <p>
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
    private Button btnSend;

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
    private TableColumn<Recipe, Number> id;

    @FXML
    private TableColumn<Recipe, String> name;

    @FXML
    private TableColumn<Recipe, Number> portions;

    @FXML
    private TableColumn<Recipe, Number> price;

    @FXML
    private TableColumn<Recipe, String> meal;

    @FXML
    private TableColumn<Recipe, String> created;

    @FXML
    private TableColumn<Recipe, String> edited;

    @FXML
    private TableColumn<RecipeItem, Number> recipeItemId;

    @FXML
    private TableColumn<RecipeItem, String> recipeItemName;

    @FXML
    private TableColumn<RecipeItem, String> description;

    @FXML
    private TableColumn<RecipeItem, String> recipeItemIngredient;

    @FXML
    private TableColumn<RecipeItem, Number> recipeItemAmount;

    @FXML
    private TableColumn<RecipeItem, Unit> recipeItemUnits;

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

        Meal meal = cmbMeal.getSelectionModel().getSelectedItem();

        recipe.setMeal(meal);
        recipe.setItems(new ArrayList<>());
        //Recipe createdRecipe = recipeRestService.create(recipe);
        recipeTableData.add(recipe);
        recipesTable.refresh();
    }

    @FXML
    void removeAction(ActionEvent event) {
        recipeRestService.delete(selectendRecipe.getId());
        recipeTableData.remove(selectendRecipe);
        recipesTable.refresh();
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
        cmbAuthor.setConverter(new NameStringConverter<>());

        ingredients = FXCollections.observableArrayList(ingredientRestService.findAll());
        cmbIngredient.setItems(ingredients);
        cmbIngredient.setConverter(new NameStringConverter<>());
        cmbUnits.setItems(FXCollections.observableArrayList(Unit.values()));

        meals = FXCollections.observableArrayList(mealRestService.findAll());
        cmbMeal.setItems(meals);

        cmbMeal.setConverter(new NameStringConverter<>());
        if (selectendRecipe != null) {
            recipeItemTableData = FXCollections.observableArrayList(selectendRecipe.getItems());
        }

    }

    private void bindRecipeItemTableColumns() {

        description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        recipeItemId.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        recipeItemName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        recipeItemIngredient.setCellValueFactory(cellData -> cellData.getValue().ingredientProperty().getValue().nameProperty());

        recipeItemAmount.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

        recipeItemUnits.setCellValueFactory(cellData -> cellData.getValue().unitProperty());

    }

    private void bindRecipeTableColumns() {

        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        portions.setCellValueFactory(cellData -> cellData.getValue().numberOfPortionProperty());

        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        meal.setCellValueFactory(cellData -> cellData.getValue().mealProperty().getValue().nameProperty());

        created.setCellValueFactory(cellData -> cellData.getValue().createdProperty().asString());

        edited.setCellValueFactory(cellData -> cellData.getValue().editedProperty().asString());

    }

    private void createDetailFieldsBinding() {

        recipesTable.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Recipe>) c -> {
            if (!c.getList().isEmpty()) {
                if (selectendRecipe != null) {
                    //                  txtName.textProperty().unbindBidirectional(selectendRecipe.nameProperty());
                }
                selectendRecipe = c.getList().get(0);
//                txtName.textProperty().bindBidirectional(selectendRecipe.nameProperty());
                recipeItemTableData = FXCollections.observableArrayList(selectendRecipe.getItems());
                recipeItemsTable.setItems(recipeItemTableData);
                bindRecipeItemTableColumns();
                recipeItemsTable.refresh();
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
        if (selectendRecipe != null) {

            RecipeItem item = new RecipeItem();
            item.setRecipe(selectendRecipe);
            item.setName(txtRecipeItemName.getText());
            item.setDescription(txtRecipeItemDescription.getText());
            item.setAmount(Double.valueOf(txtAmount.getText()));
            item.setUnit((Unit) cmbUnits.getSelectionModel().getSelectedItem());
            item.setIngredient(cmbIngredient.getSelectionModel().getSelectedItem());
            List<RecipeItem> items = selectendRecipe.getItems();
            if (items == null) {
                items = FXCollections.observableArrayList();
            }
            items.add(item);
            //recipeRestService.update(selectendRecipe);

            //RecipeItem createdRecipeItem = recipeItemRestService.create(item);
            if (recipeItemTableData == null) {
                recipeItemTableData = FXCollections.observableArrayList();
            }
            recipeItemTableData.setAll(items);
            recipeItemsTable.refresh();
        }
    }

    @FXML
    void updateItem(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    @FXML
    void sendAction(ActionEvent event) {
        UserEvent creationEvent = new UserEvent();
        Author author = cmbAuthor.getSelectionModel().getSelectedItem();
        creationEvent.setAuthor(author);
        creationEvent.setEventTime(new Date());
        UserEvent savedEvent = userEventRestService.create(creationEvent);


        selectendRecipe.setEdited(savedEvent);


        if (selectendRecipe.getId() != 0) {
            recipeRestService.update(selectendRecipe);
        } else {
            selectendRecipe.setCreated(savedEvent);
            recipeRestService.create(selectendRecipe);
        }
    }

}
