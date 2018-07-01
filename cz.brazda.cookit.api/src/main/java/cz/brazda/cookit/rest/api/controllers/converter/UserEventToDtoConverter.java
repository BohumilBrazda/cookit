package cz.brazda.cookit.rest.api.controllers.converter;

import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import cz.brazda.cookit.repository.entity.UserEvent;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventToDtoConverter extends AbstractConverter<UserEvent, UserEventDto>{

    @Autowired
    AuthorToDtoConverter authorToDtoConverter;

    @Override
    protected UserEventDto convert(UserEvent source) {
        AuthorDto author = authorToDtoConverter.convert(source.getAuthor());
        return source == null ? null : new UserEventDto(source.getId(), source.getEventTime(), author);
    }
}