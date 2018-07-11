package client.repository.service.remote.rest.converters;

import client.repository.model.UserEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cz.brazda.cookit.common.dto.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserEventNodeToDtoConverter extends AbstractConverter<ObjectNode, UserEventDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected UserEventDto convert(ObjectNode source) {
        if(source != null){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS",Locale.forLanguageTag("Europe/Prague"));

            GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("cs_CZ"));
            calendar.setTimeInMillis(source.get("eventTime").longValue());

            AuthorDto authorDto = modelMapper.map(source.get("author"), AuthorDto.class);
            return new UserEventDto(source.get("id").longValue(), calendar.getTime(), authorDto);
        }
        return null;
    }


}