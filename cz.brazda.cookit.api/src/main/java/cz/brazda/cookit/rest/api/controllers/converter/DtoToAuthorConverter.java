package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.repository.entity.Author;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class DtoToAuthorConverter extends AbstractConverter<AuthorDto, Author>{

    @Override
    protected Author convert(AuthorDto source) {
        return source == null ? null : new Author(source.getId(), source.getFirstName(), source.getSecondName());
    }
}