package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.RecipeItem;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToRecipeConverter extends AbstractConverter<RecipeDto, Recipe> {

    @Autowired
    private DtoToUserEventConverter dtoToUserEventConverter;

    @Autowired
    private DtoToMealConverter dtoToMealConverter;

    @Autowired
    private DtoToRecipeItemConverter dtoToRecipeItemConverter;

    @Override
    protected Recipe convert(RecipeDto source) {

        if(source != null){
            Recipe recipe = new Recipe(source.getId(), source.getName(), source.getNumberOfPortion(), source.getPrice(), dtoToUserEventConverter.convert(source.getCreated()), dtoToUserEventConverter.convert(source.getEdited()), dtoToMealConverter.convert(source.getMeal()));
            addRecipeItemsToRecipe(source.getItems(), recipe);
            return recipe;
        }
        return new Recipe();

    }

    private void addRecipeItemsToRecipe(List<RecipeItemDto> sourceItems, Recipe recipe) {
        if(sourceItems != null){
            List<RecipeItem> items = new ArrayList<>();
            for (RecipeItemDto recipeItemDto: sourceItems){
                RecipeItem recipeItem  = dtoToRecipeItemConverter.convert(recipeItemDto);
                recipeItem.setRecipe(recipe);
                items.add(recipeItem);
            }
            recipe.setItems(items);
        }
    }
}