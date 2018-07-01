package client.repository.service.remote.rest.converters;

import client.repository.model.Author;
import client.repository.model.UserEvent;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToUserEventConverter extends AbstractConverter<UserEventDto, UserEvent> {

    @Autowired
    private DtoToAuthorConverter dtoToAuthorConverter;

    @Override
    protected UserEvent convert(UserEventDto source) {
        Author author = dtoToAuthorConverter.convert(source.getAuthor());
        return source == null ? null : new UserEvent(source.getId(),  author, source.getEventTime());
    }
}