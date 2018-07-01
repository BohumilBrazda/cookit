package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.repository.entity.Author;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToDtoConverter extends AbstractConverter<Author, AuthorDto>{

    @Override
    protected AuthorDto convert(Author source) {
        return source == null ? null : new AuthorDto(source.getId(), source.getFirstName(), source.getSecondName());
    }
}