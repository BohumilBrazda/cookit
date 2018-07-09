package cz.brazda.cookit.common.dto;

import cz.brazda.cookit.common.Unit;

/**
 * DTO object for RecipeItem entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class RecipeItemDto implements EntityDto {

    private Long id;

    private String name;
    private String description;
    private Double amount;
    private IngredientDto ingredient;
    private Unit unit;

    //private RecipeDto recipeDto;

    public RecipeItemDto(Long id, String name, String description, Double amount, IngredientDto ingredient, Unit unit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.ingredient = ingredient;
        this.unit = unit;
    }

    public RecipeItemDto() {
    }

   // public RecipeDto getRecipeDto(){return recipeDto; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }

//    public void setRecipeDto(RecipeDto recipeDto){
//        this.recipeDto = recipeDto;
//    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeItemDto that = (RecipeItemDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (ingredient != null ? !ingredient.equals(that.ingredient) : that.ingredient != null) return false;
        return unit == that.unit;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (ingredient != null ? ingredient.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecipeItemDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", ingredient=" + ingredient +
                ", unit=" + unit +
                '}';
    }
}
