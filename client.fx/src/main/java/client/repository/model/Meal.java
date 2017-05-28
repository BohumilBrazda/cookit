package client.repository.model;


import cz.brazda.cookit.common.IdElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */
public class Meal implements Entity {

    private Long id;
    private String name;
    private String description;

    private List<Object> pictures;

    private List<Recipe> recipes;

    public Meal(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Meal() {
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Object> getPictures() {
        return pictures;
    }

    public void setPictures(List<Object> pictures) {
        this.pictures = pictures;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }
}
