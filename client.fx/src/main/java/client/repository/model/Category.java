package client.repository.model;


import cz.brazda.cookit.common.IdElement;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BOBES on 8.2.2015.
 */
public class Category implements Entity {

    private Long id;
    private String description;
    private String categoryName;

    private List<Object> pictures;

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
