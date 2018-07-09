package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DtoToRecipeItemConverter extends AbstractConverter<RecipeItemDto, RecipeItem> {

    @Autowired
    DtoToMealConverter dtoToMealConverter;

    @Autowired
    DtoToIngredientConverter dtoToIngredientConverter;

    @Override
    protected RecipeItem convert(RecipeItemDto source) {
        return source == null ? null : new RecipeItem(source.getId(), null, source.getName(), source.getDescription(), source.getAmount(),dtoToIngredientConverter.convert(source.getIngredient()), source.getUnit());
    }
}