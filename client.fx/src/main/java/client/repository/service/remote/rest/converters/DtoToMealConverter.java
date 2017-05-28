package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import cz.brazda.cookit.common.dto.MealDto;

import org.modelmapper.AbstractConverter;

public class DtoToMealConverter extends AbstractConverter<MealDto, Meal> {

    @Override
    protected Meal convert(MealDto source) {
        return source == null ? null : new Meal(source.getId(), source.getName(), source.getDescription());
    }
}