package client.repository.service.remote.rest.converters;

import client.repository.model.Ingredient;
import cz.brazda.cookit.common.dto.IngredientDto;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DtoToIngredientConverter extends AbstractConverter<IngredientDto, Ingredient> {

    @Override
    protected Ingredient convert(IngredientDto source) {
        return source == null ? null : new Ingredient(source.getId(), source.getName(), source.getDescription());
    }
}