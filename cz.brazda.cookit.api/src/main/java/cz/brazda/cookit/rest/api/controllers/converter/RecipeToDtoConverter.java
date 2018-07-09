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
        if(source != null){
            RecipeDto recipeDto = new RecipeDto(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), userEventToDtoConverter.convert(source.getCreated()), userEventToDtoConverter.convert(source.getEdited()), mealToDtoConverter.convert(source.getMeal()));
            addRecipeItemsToRecipe(source.getItems(), recipeDto);
            return recipeDto;
        }
        return new RecipeDto();
    }

    private void addRecipeItemsToRecipe(List<RecipeItem> sourceItems, RecipeDto recipeDto) {
        if(sourceItems != null){
            List<RecipeItemDto> itemDtos = new ArrayList<>();
            for(RecipeItem item: sourceItems){
                RecipeItemDto recipeItemDto = recipeItemToDtoConverter.convert(item);
                itemDtos.add(recipeItemDto);
            }
            recipeDto.setItems(itemDtos);
        }

    }
}