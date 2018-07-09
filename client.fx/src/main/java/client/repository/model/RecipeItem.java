package client.repository.model;

import cz.brazda.cookit.common.Unit;
import javafx.beans.property.*;

/**
 * Created by BOBES on 15.8.2015.
 */
public class RecipeItem implements Entity {

    private LongProperty id = new SimpleLongProperty();

    private ObjectProperty<Recipe> recipe = new SimpleObjectProperty<>();
    private ObjectProperty<Ingredient> ingredient =  new SimpleObjectProperty<>();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>();

    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();

    public RecipeItem() {
    }

    public RecipeItem(Long id, Recipe recipe, String name, String description, Double amount, Ingredient ingredient,  Unit unit) {
        this.id.setValue(id);
        this.recipe.setValue(recipe);
        this.name.setValue(name);

        this.description.setValue(description);
        this.ingredient.setValue(ingredient);
        this.amount.setValue(amount);
        this.unit.set(unit);
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public String getDescription() {
        return description.get();
    }

    public Ingredient getIngredient() {
        return ingredient.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public Unit getUnit() {
        return unit.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setDescription(String description) {
        this.description.setValue(description);
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient.setValue(ingredient);
    }

    public void setAmount(Double amount) {
        this.amount.setValue(amount);
    }

    public void setUnit(Unit unit) {
        this.unit.set(unit);
    }

    public Recipe getRecipe() {
        return recipe.get();
    }

    public void setRecipe(Recipe recipe) {
        this.recipe.setValue(recipe);
    }

    @Override
    public Long getId() {
        return this.id.get();
    }

    public ObjectProperty<Recipe> recipeProperty() {
        return recipe;
    }

    public ObjectProperty<Ingredient> ingredientProperty() {
        return ingredient;
    }

    public ObjectProperty<Unit> unitProperty() {
        return unit;
    }
}
