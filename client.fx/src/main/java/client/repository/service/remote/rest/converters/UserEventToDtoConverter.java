package client.repository.service.remote.rest.converters;

import client.repository.model.UserEvent;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventToDtoConverter extends AbstractConverter<UserEvent, UserEventDto> {

    @Autowired
    private AuthorToDtoConverter authorToDtoConverter;

    @Override
    protected UserEventDto convert(UserEvent source) {
        AuthorDto authorDto = authorToDtoConverter.convert(source.getAuthor());
        return source == null ? null : new UserEventDto(source.getId(), source.getEventTime(), authorDto);
    }
}