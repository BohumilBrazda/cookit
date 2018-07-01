package client.repository.service.remote.rest.converters;

import client.repository.model.Author;
import client.repository.model.Meal;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.common.dto.MealDto;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAuthorConverter extends AbstractConverter<AuthorDto, Author> {

    @Override
    protected Author convert(AuthorDto source) {
        return source == null ? null : new Author(source.getId(), source.getFirstName(), source.getSecondName());
    }
}