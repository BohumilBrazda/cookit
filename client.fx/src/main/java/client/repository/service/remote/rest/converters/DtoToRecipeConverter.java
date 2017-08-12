package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import cz.brazda.cookit.common.dto.RecipeDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DtoToRecipeConverter extends AbstractConverter<RecipeDto, Recipe> {

    @Autowired
    DtoToMealConverter dtoToMealConverter;

    @Override
    protected Recipe convert(RecipeDto source) {
        if(source == null){
            return null;
        }else{
            Recipe recipe = new Recipe(source.getId(), source.getName());

            Meal meal = dtoToMealConverter.convert(source.getMeal());
            recipe.setMeal(meal);
            return recipe;
        }
    }
}