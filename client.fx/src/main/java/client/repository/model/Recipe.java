package client.repository.model;


import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */


public class Recipe implements Entity {

    private LongProperty id = new SimpleLongProperty();

    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty numberOfPortion = new SimpleIntegerProperty();
    private FloatProperty price = new SimpleFloatProperty();
    private List<RecipeItem> items = new ArrayList<>();

    private List<Category> categories;
    private ObjectProperty<UserEvent> created = new SimpleObjectProperty<>();

    private ObjectProperty<UserEvent> edited = new SimpleObjectProperty<>();
    private ObjectProperty<Meal> meal = new SimpleObjectProperty<>();

    public Recipe() {
    }

    public Recipe(Long id, String name, Integer numberOfPortion, Float price, List<Category> categories, UserEvent created, UserEvent edited, Meal meal) {
        this.id.set(id);
        this.name.set(name);
        this.numberOfPortion.set(numberOfPortion);
        this.price.set(price);
        this.categories = categories;
        this.created.set(created);
        this.edited.set(edited);
        this.meal.set(meal);
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
        return meal.get();
    }

    public void setMeal(Meal meal) {
        this.meal.set(meal);
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
        return created.get();
    }

    public UserEvent getEdited() {
        return edited.get();
    }

    public void setCreated(UserEvent created) {
        this.created.set(created);
    }

    public void setEdited(UserEvent edited) {
        this.edited.set(edited);
    }

    public ObjectProperty<UserEvent> createdProperty() {
        return created;
    }

    public ObjectProperty<UserEvent> editedProperty() {
        return edited;
    }

    public ObjectProperty<Meal> mealProperty() {
        return meal;
    }
}
