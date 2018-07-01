package client.repository.model;


import javafx.beans.property.*;

import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */


public class Recipe implements Entity {

    private LongProperty id = new SimpleLongProperty();

    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty numberOfPortion = new SimpleIntegerProperty();
    private FloatProperty price = new SimpleFloatProperty();
    private List<RecipeItem> items;

    private List<Category> categories;
    private UserEvent created;

    private UserEvent edited;
    private Meal meal;

    public Recipe() {
    }

    public Recipe(Long id, String name, Integer numberOfPortion, Float price, List<RecipeItem> items, List<Category> categories, UserEvent created, UserEvent edited, Meal meal) {
        this.id.setValue(id);
        this.name.setValue(name);
        this.numberOfPortion.setValue(numberOfPortion);
        this.price.setValue(price);
        this.items = items;
        this.categories = categories;
        this.created = created;
        this.edited = edited;
        this.meal = meal;
    }

    public Recipe(Long id, String name) {
        this.id.setValue(id);
        this.name.setValue(name);
    }

    public Recipe(String name) {
        this.name.setValue(name);
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty numberOfPortionProperty() {
        return numberOfPortion;
    }

    public void setNumberOfPortion(int numberOfPortion) {
        this.numberOfPortion.set(numberOfPortion);
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.setValue(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<RecipeItem> getItems() { return items;   }

    public void setItems(List<RecipeItem> items) {
        this.items = items;
    }

    public Integer getNumberOfPortion() {
        return numberOfPortion.get();
    }

    public void setNumberOfPortion(Integer numberOfPortion) {
        this.numberOfPortion.set(numberOfPortion);
    }

    public Float getPrice() {
        return price.get();
    }

    public void setPrice(Float price) {
        this.price.set(price);
    }

    public UserEvent getCreated() {
        return created;
    }

    public UserEvent getEdited() {
        return edited;
    }

    public void setCreated(UserEvent created) {
        this.created = created;
    }

    public void setEdited(UserEvent edited) {
        this.edited = edited;
    }

}
