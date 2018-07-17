package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.RecipeItem;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeItemToDtoConverter extends AbstractConverter<RecipeItem, RecipeItemDto> {

    @Autowired
    private IngredientToDtoConverter ingredientToDtoConverter;

    @Override
    protected RecipeItemDto convert(RecipeItem source) {
        return source == null ? null : new RecipeItemDto(source.getId(), source.getName(),source.getDescription(), source.getAmount(), ingredientToDtoConverter.convert(source.getIngredient()), source.getUnit());
    }
}