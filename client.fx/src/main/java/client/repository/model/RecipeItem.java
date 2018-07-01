package client.repository.model;

import cz.brazda.cookit.common.Unit;

/**
 * Created by BOBES on 15.8.2015.
 */
public class RecipeItem implements Entity {

    private Long id;
    private Recipe recipe;
    private String name;
    private String description;
    private Ingredient ingredient;
    private Double amount;
    private Unit unit;

    public RecipeItem() {
    }

    public RecipeItem(Long id, Recipe recipe, String name, String description, Double amount, Ingredient ingredient,  Unit unit) {
        this.id = id;
        this.recipe = recipe;
        this.name = name;

        this.description = description;
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Double getAmount() {
        return amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public Long getId() {
        return this.id;
    }
}
