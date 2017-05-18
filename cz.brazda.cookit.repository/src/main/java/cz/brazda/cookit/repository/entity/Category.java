package cz.brazda.cookit.repository.entity;


import cz.brazda.cookit.common.IdElement;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */
@Entity
@Table(name = "category")
public class Category implements IdElement, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String categoryName;

    @Transient
    private List<Object> pictures;

    //constructor for hibernate use
    public Category() {
    }

    public Category(String categoryName, String description, List<Object> pictures) {
        this.description = description;
        this.categoryName = categoryName;
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Object> getPictures() {
        return pictures;
    }

    public void setPictures(List<Object> pictures) {
        this.pictures = pictures;
    }

}
