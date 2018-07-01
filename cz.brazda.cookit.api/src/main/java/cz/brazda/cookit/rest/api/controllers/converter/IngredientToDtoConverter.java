package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.repository.entity.Ingredient;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToDtoConverter extends AbstractConverter<Ingredient, IngredientDto>{

    @Override
    protected IngredientDto convert(Ingredient source) {
        return source == null ? null : new IngredientDto(source.getId(), source.getName(), source.getDescription());
    }
}