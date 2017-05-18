package cz.brazda.cookit.repository.entity;


import cz.brazda.cookit.common.IdElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */

@Entity
@Table(name = "recipe")
public class Recipe implements IdElement, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "portions")
    private Integer numberOfPortion;

    @Column(name = "price")
    private Float price;

    @OneToMany(mappedBy="recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeItem> items;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    @ManyToOne()
    @JoinColumn(name = "created_id")
    UserEvent created;

    @ManyToOne()
    @JoinColumn(name = "edited_id")
    UserEvent edited;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    //hibernate
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
