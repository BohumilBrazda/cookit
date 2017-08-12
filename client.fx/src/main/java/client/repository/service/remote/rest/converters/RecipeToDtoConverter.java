package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import cz.brazda.cookit.common.dto.MealDto;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.rest.api.controllers.converter.MealToDtoConverter;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class RecipeToDtoConverter extends AbstractConverter<Recipe, RecipeDto> {

    @Override
    protected RecipeDto convert(Recipe source) {
        if(source == null){
            return null;
        }else{
            RecipeDto recipeDto = new RecipeDto(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice());
            return recipeDto;
        }
    }
}