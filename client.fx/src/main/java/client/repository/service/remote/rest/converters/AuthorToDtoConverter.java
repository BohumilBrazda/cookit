package client.repository.service.remote.rest.converters;

import client.repository.model.Author;
import cz.brazda.cookit.common.dto.AuthorDto;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthorToDtoConverter extends AbstractConverter<Author, AuthorDto> {

    @Override
    protected AuthorDto convert(Author source) {
        return source == null ? null : new AuthorDto(source.getId(), source.getFirstName(), source.getSecondName());
    }
}