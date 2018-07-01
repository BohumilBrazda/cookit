package cz.brazda.cookit.common.dto;

/**
 * DTO object for Ingredient entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class IngredientDto implements EntityDto {

    private Long id;
    private String name;
    private String description;

    public IngredientDto() {
    }

    public IngredientDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IngredientDto that = (IngredientDto) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
