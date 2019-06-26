package cz.brazda.cookit.repository.entity;


import cz.brazda.cookit.common.IdElement;

import javax.persistence.*;


/**
 * Created by BOBES on 8.2.2015.
 */

@Entity
@Table(name = "author")
public class Author implements IdElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    //constructor for hibernate
    public Author() {
    }

    public Author(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Author(Long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFullName() {
        return this.firstName + " " + this.getSecondName();
    }

    @Override
    public String toString() {
        String name = null;
        name = (firstName == null ? "" : firstName) + (secondName == null ? "" : (" " + secondName));

        return name == null ? super.toString() : name;
    }
}
