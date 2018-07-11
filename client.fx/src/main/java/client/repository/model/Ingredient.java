package client.repository.model;

import cz.brazda.cookit.common.IdElement;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Created by BOBES on 16.8.2015.
 */
public class Ingredient implements Entity {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public Ingredient() {
    }

    public Ingredient(Long id, String name, String description) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
    }

    public void setId(long id) {
        this.id.set(id);
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

    public String getDescription() {
        return description.get();
    }

    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
