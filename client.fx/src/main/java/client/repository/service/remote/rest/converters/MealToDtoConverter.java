package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import cz.brazda.cookit.common.dto.MealDto;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class MealToDtoConverter extends AbstractConverter<Meal, MealDto> {

    @Override
    protected MealDto convert(Meal source) {
        return source == null ? null : new MealDto(source.getId(), source.getName(), source.getDescription());
    }
}