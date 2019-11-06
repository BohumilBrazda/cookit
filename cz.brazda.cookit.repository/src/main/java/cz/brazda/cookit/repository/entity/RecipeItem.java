package cz.brazda.cookit.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.Unit;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BOBES on 15.8.2015.
 */
@Entity
@Table(name = "recipe_item")
@Transactional
public class RecipeItem implements IdElement,Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "unit")
    private Unit unit;

    //Hibernate
    public RecipeItem() {
    }

    public RecipeItem(Long id, String name, String description, Double amount, Ingredient ingredient, Unit unit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.ingredient = ingredient;
        this.unit = unit;
    }

    public RecipeItem(RecipeItem sourceItem) {
        this.name = sourceItem.name;
        this.recipe = sourceItem.recipe;
        this.description = sourceItem.description;
        this.amount = sourceItem.amount;
        this.ingredient = sourceItem.ingredient;
        this.unit = sourceItem.unit;
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeItem that = (RecipeItem) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
