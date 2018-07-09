package client.repository.service.remote.rest.converters;

import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
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
            RecipeDto recipeDto = new RecipeDto(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), userEventToDtoConverter.convert(source.getCreated()), userEventToDtoConverter.convert(source.getCreated()), mealToDtoConverter.convert(source.getMeal()));
            addItemsToRecipe(source.getItems(), recipeDto);
            return recipeDto;
        }
        return new RecipeDto();
    }

    private void addItemsToRecipe(List<RecipeItem> sourceItems, RecipeDto recipeDto) {
        if(sourceItems != null ){
            List<RecipeItemDto> itemDtos = new ArrayList<>();
            for(RecipeItem recipeItem:sourceItems){
                RecipeItemDto recipeItemDto = recipeItemToDtoConverter.convert(recipeItem);
                itemDtos.add(recipeItemDto);
            }
            recipeDto.setItems(itemDtos);
        }
    }
}