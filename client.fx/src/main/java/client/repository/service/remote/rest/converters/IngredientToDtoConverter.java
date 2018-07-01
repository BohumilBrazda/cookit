package client.repository.service.remote.rest.converters;

import client.repository.model.Ingredient;
import cz.brazda.cookit.common.dto.IngredientDto;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToDtoConverter extends AbstractConverter<Ingredient, IngredientDto> {

    @Override
    protected IngredientDto convert(Ingredient source) {
        return source == null ? null : new IngredientDto(source.getId(), source.getName(), source.getDescription());
    }
}