package client.repository.service.remote.rest.converters;

import client.repository.model.Meal;
import cz.brazda.cookit.common.dto.MealDto;
import org.modelmapper.AbstractConverter;

import javax.json.JsonValue;

public class JsonToMealDtoConverter extends AbstractConverter<JsonValue, MealDto> {

    @Override
    protected MealDto convert(JsonValue source) {
        // return source == null ? null : new Meal(source.asJsonArray()., source.getName(), source.getDescription());
        return null;
    }
}