package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.MealDto;
import cz.brazda.cookit.repository.entity.Meal;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DtoToMealConverter extends AbstractConverter<MealDto, Meal> {

    @Override
    protected Meal convert(MealDto source) {
        return source == null ? null : new Meal(source.getId(), source.getName(), source.getDescription());
    }
}
