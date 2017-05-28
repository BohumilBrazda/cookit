package client.repository.model;


import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */


public class Recipe implements Entity {

    private Long id;
    private String name;
    private Integer numberOfPortion;
    private Float price;

    private List<RecipeItem> items;
    private List<Category> categories;

    private UserEvent created;
    private UserEvent edited;

    private Meal meal;

    public Recipe() {
    }

    public Recipe(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Long getId() {
        return id;
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
        return numberOfPortion;
    }

    public void setNumberOfPortion(Integer numberOfPortion) {
        this.numberOfPortion = numberOfPortion;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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