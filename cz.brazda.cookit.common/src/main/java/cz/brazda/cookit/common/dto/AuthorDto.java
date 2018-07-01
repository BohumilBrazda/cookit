package cz.brazda.cookit.common.dto;

/**
 * DTO object for Author entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class AuthorDto implements EntityDto {

    private Long id;
    private String firstName;
    private String secondName;

    @Override
    public Long getId() {
        return id;
    }

    public AuthorDto() {
    }

    public AuthorDto(Long id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDto authorDto = (AuthorDto) o;

        return id.equals(authorDto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getFullName(){
        return firstName + " " + secondName;
    }
}
