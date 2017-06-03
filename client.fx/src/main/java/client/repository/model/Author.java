package client.repository.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Client entity model of Author
 * Created by Bohumil Brázda on 22.5.2017.
 */
public class Author implements Entity {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty secondName = new SimpleStringProperty();

    public Author() {
    }

    public Author(String firstName, String secondName) {
        setFirstName(firstName);
        setSecondName(secondName);
    }

    public void setId(Long id) {
        this.id.setValue(id);
    }

    @Override
    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName.setValue(secondName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public String getFullName() {
        return firstName.get() + " " + secondName.get();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id.get() +
                ", firstName='" + firstName.get() + '\'' +
                ", secondName='" + secondName.get() + '\'' +
                '}';
    }
}
