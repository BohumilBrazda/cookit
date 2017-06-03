package client.repository.model;


import cz.brazda.cookit.common.IdElement;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */
public class Meal implements Entity {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();

    private StringProperty description = new SimpleStringProperty();

    private List<Object> pictures;

    private List<Recipe> recipes;

    public Meal(Long id, String name, String description) {
        this.id.setValue(id);
        this.name.setValue(name);
        this.description.setValue(description);
    }

    public Meal(String name, String description) {
        this.name.setValue(name);
        this.description.setValue(description);
    }

    public Meal() {
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

    public void setId(Long id) {
        this.id.setValue(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.setValue(description);
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
        return id.get();
    }
}
