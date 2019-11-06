package cz.brazda.cookit.repository.entity;


import cz.brazda.cookit.common.IdElement;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */
@Entity
@Table(name = "meal")
public class Meal implements IdElement, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<Image> pictures;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<Recipe> recipes;

    public Meal(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                '}';
    }

    //hibernate
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

    public List<Image> getPictures() {
        return pictures;
    }

    public void setPictures(List<Image> pictures) {
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
