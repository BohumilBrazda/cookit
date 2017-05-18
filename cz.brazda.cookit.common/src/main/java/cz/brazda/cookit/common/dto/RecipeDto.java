package cz.brazda.cookit.common.dto;

import java.util.List;

/**
 * Created by virtual on 2.5.2017.
 */
public class RecipeDto extends AbstractDto {

    private String name;
    private Integer numberOfPortion;
    private Float price;

    private List<RecipeItemDto> items;

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

        if (!name.equals(recipeDto.name)) return false;
        if (numberOfPortion != null ? !numberOfPortion.equals(recipeDto.numberOfPortion) : recipeDto.numberOfPortion != null)
            return false;
        return price != null ? price.equals(recipeDto.price) : recipeDto.price == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (numberOfPortion != null ? numberOfPortion.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
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
