package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.Category;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.RecipeItem;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeToDtoConverter extends AbstractConverter<Recipe, RecipeDto> {

    @Autowired
    private UserEventToDtoConverter userEventToDtoConverter;

    @Autowired
    private MealToDtoConverter mealToDtoConverter;

    @Autowired
    private RecipeItemToDtoConverter recipeItemToDtoConverter;

    @Override
    protected RecipeDto convert(Recipe source) {
        return source == null ? null : new RecipeDto(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), getRecipeItemDtos(source), userEventToDtoConverter.convert(source.getCreated()), userEventToDtoConverter.convert(source.getEdited()), mealToDtoConverter.convert(source.getMeal()));
    }

    private List<RecipeItemDto> getRecipeItemDtos(Recipe source) {
        if(source.getItems() != null && !source.getItems().isEmpty()){
            List<RecipeItemDto> items = new ArrayList<>();
            source.getItems().forEach(item->items.add(recipeItemToDtoConverter.convert(item)));
        }

        return null;

    }
}