package client.repository.service.remote.rest.converters;

import client.repository.model.RecipeItem;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeItemToDtoConverter extends AbstractConverter<RecipeItem, RecipeItemDto> {

    @Autowired
    MealToDtoConverter mealToDtoConverter;

    @Autowired
    RecipeToDtoConverter recipeToDtoConverter;

    @Autowired
    IngredientToDtoConverter ingredientToDtoConverter;

    @Override
    protected RecipeItemDto convert(RecipeItem source) {
        RecipeDto recipe =  recipeToDtoConverter.convert(source.getRecipe());
        return source == null ? null : new RecipeItemDto(source.getId(), recipe, source.getName(), source.getDescription(), source.getAmount(),ingredientToDtoConverter.convert(source.getIngredient()), source.getUnit());
    }
}