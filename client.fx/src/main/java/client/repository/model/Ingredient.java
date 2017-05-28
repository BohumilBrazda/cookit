package client.repository.model;

import cz.brazda.cookit.common.IdElement;

import java.io.Serializable;

/**
 * Created by BOBES on 16.8.2015.
 */
public class Ingredient implements Entity {

    private Long id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
