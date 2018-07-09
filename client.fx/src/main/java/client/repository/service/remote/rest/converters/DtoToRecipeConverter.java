package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
import client.repository.model.UserEvent;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToRecipeConverter extends AbstractConverter<RecipeDto, Recipe> {

    @Autowired
    DtoToMealConverter dtoToMealConverter;

    @Autowired
    DtoToUserEventConverter dtoToUserEventConverter;

    @Autowired
    DtoToRecipeItemConverter dtoToRecipeItemConverter;

    @Override
    protected Recipe convert(RecipeDto source) {
        if(source == null){
            return null;
        }else{
            UserEvent created = dtoToUserEventConverter.convert(source.getCreated());
            UserEvent edited = dtoToUserEventConverter.convert(source.getEdited());
            Meal meal = dtoToMealConverter.convert(source.getMeal());

            Recipe recipe = new Recipe(source.getId(),source.getName(), source.getNumberOfPortion(), source.getPrice(), null, created, edited, meal);
            addItemstoRecipe(source.getItems(), recipe);
            return recipe;
        }
    }

    private void addItemstoRecipe(List<RecipeItemDto> sourceItems, Recipe sourceRecipe) {
        if(sourceItems != null){
            List<RecipeItem> items = new ArrayList<>();
            for (RecipeItemDto itemDto: sourceItems) {
                RecipeItem convertedItem = dtoToRecipeItemConverter.convert(itemDto);
                convertedItem.setRecipe(sourceRecipe);
                items.add(convertedItem);
            }
            sourceRecipe.getItems().addAll(items);
        }
    }
}