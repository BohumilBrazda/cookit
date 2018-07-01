package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.repository.entity.Ingredient;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DtoToIngredientConverter extends AbstractConverter<IngredientDto, Ingredient>{

    @Override
    protected Ingredient convert(IngredientDto source) {
        return source == null ? null : new Ingredient(source.getId(), source.getName(), source.getDescription());
    }
}