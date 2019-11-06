package cz.brazda.cookit.repository.entity;

import cz.brazda.cookit.common.IdElement;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BOBES on 16.8.2015.
 */
@Entity
@Table(name = "ingredient")
public class Ingredient implements IdElement, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }

    @Column(name = "description")
    private String description;

    //Hibernate
    public Ingredient() {
    }

    public Ingredient(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

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
