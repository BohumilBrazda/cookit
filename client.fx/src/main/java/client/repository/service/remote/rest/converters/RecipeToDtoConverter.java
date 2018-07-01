package client.repository.service.remote.rest.converters;

import client.repository.model.Recipe;
import cz.brazda.cookit.common.dto.MealDto;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.common.dto.UserEventDto;
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
        if(source != null){
            List<RecipeItemDto> itemDtos = getRecipeItemDtos(source);

            return source == null ? null : new RecipeDto(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), itemDtos, userEventToDtoConverter.convert(source.getCreated()), userEventToDtoConverter.convert(source.getCreated()), mealToDtoConverter.convert(source.getMeal()));

        }
        return null;
    }

    private List<RecipeItemDto> getRecipeItemDtos(Recipe source) {
        if(source.getItems() != null && !source.getItems().isEmpty()){
            List<RecipeItemDto> itemDtos = new ArrayList<>();
            source.getItems().forEach(item -> itemDtos.add(recipeItemToDtoConverter.convert(item)));
        }
        return null;
    }
}