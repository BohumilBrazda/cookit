package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DtoToRecipeListConverter extends AbstractConverter<List<RecipeItemDto>, List<RecipeItem>> {

    @Autowired
    DtoToMealConverter dtoToMealConverter;



    @Override
    protected List<RecipeItem> convert(List<RecipeItemDto> source) {
        List<RecipeItem> convertedRecipes = new ArrayList<>();
        for (RecipeItemDto sourceRecipe: source
             ) {


        }
        return convertedRecipes;
    }
}