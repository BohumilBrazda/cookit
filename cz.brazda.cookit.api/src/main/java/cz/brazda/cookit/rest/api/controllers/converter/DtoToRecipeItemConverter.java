package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.RecipeItem;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToRecipeItemConverter extends AbstractConverter<RecipeItemDto, RecipeItem> {

    @Autowired
    private DtoToIngredientConverter dtoToIngredientConverter;

    @Autowired
    private DtoToRecipeConverter dtoToRecipeConverter;

    @Override
    protected RecipeItem convert(RecipeItemDto source) {
        return source == null ? null : new RecipeItem(source.getId(), dtoToRecipeConverter.convert(source.getRecipeDto()), source.getName(), "",source.getAmount(), dtoToIngredientConverter.convert(source.getIngredient()), source.getUnit());
    }
}