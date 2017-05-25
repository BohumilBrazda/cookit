package cz.brazda.cookit.common.dto;

import java.util.List;

/**
 * DTO object for Meal entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class MealDto implements EntityDto {

    private Long id;
    private String name;
    private String description;

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

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDto> recipes) {
        this.recipes = recipes;
    }

    private List<RecipeDto> recipes;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealDto mealDto = (MealDto) o;

        if (id != null ? !id.equals(mealDto.id) : mealDto.id != null) return false;
        return name != null ? name.equals(mealDto.name) : mealDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MealDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
