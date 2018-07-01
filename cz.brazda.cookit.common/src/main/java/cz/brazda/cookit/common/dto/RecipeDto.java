package cz.brazda.cookit.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO object for Recipe entity
 * Created by Bohumil Brázda on 14.5.2017.
 */
public class RecipeDto implements EntityDto {

    private Long id;
    private String name;
    private Integer numberOfPortion;
    private Float price;

    private List<RecipeItemDto> items;
    private MealDto meal;
    private UserEventDto created;
    private UserEventDto edited;

    public RecipeDto() {
    }

    public RecipeDto(Long id, String name, Integer numberOfPortion, Float price, List<RecipeItemDto> items, UserEventDto created, UserEventDto edited, MealDto meal) {
        this.id = id;
        this.name = name;
        this.numberOfPortion = numberOfPortion;
        this.price = price;
        this.created = created;
        this.edited = edited;
        this.meal = meal;
        this.items = items;
    }

    @Override
    public Long getId() {
        return id;
    }

    public MealDto getMeal() {
        return meal;
    }

    public void setMeal(MealDto meal) {
        this.meal = meal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPortion() {
        return numberOfPortion;
    }

    public void setNumberOfPortion(Integer numberOfPortion) {
        this.numberOfPortion = numberOfPortion;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<RecipeItemDto> getItems() {
        return items;
    }

    public void setItems(List<RecipeItemDto> items) {
        this.items = items;
    }

    public UserEventDto getCreated() {
        return created;
    }

    public void setCreated(UserEventDto created) {
        this.created = created;
    }

    public UserEventDto getEdited() {
        return edited;
    }

    public void setEdited(UserEventDto edited) {
        this.edited = edited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeDto recipeDto = (RecipeDto) o;

        if (id != null ? !id.equals(recipeDto.id) : recipeDto.id != null) return false;
        if (name != null ? !name.equals(recipeDto.name) : recipeDto.name != null) return false;
        if (numberOfPortion != null ? !numberOfPortion.equals(recipeDto.numberOfPortion) : recipeDto.numberOfPortion != null)
            return false;
        if (price != null ? !price.equals(recipeDto.price) : recipeDto.price != null) return false;
        return items != null ? items.equals(recipeDto.items) : recipeDto.items == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (numberOfPortion != null ? numberOfPortion.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RecipeDto{" +
                "name='" + name + '\'' +
                ", numberOfPortion=" + numberOfPortion +
                ", price=" + price +
                '}';
    }
}
