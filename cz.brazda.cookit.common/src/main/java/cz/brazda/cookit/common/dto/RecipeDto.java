package cz.brazda.cookit.common.dto;

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

    @Override
    public Long getId() {
        return id;
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
