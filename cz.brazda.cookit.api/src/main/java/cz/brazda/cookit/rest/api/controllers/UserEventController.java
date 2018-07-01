package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.common.dto.UserEventDto;
import cz.brazda.cookit.repository.entity.UserEvent;
import cz.brazda.cookit.repository.entity.exceptions.UserEventNotFound;
import cz.brazda.cookit.repository.service.UserEventService;
import cz.brazda.cookit.rest.api.controllers.converter.DtoToUserEventConverter;
import cz.brazda.cookit.rest.api.controllers.converter.UserEventToDtoConverter;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by virtual on 20.5.2017.
 */

@RestController
@RequestMapping(value="/userEvent")
@Consumes(MediaType.APPLICATION_JSON)
public class UserEventController extends AbstractController<UserEvent, UserEventDto> {

    private UserEventService userEventService;

    @Autowired
    public UserEventController(ModelMapper modelMapper, UserEventService userEventService) {
        super(modelMapper);
        this.userEventService = userEventService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody
    List<UserEventDto> findAll() {
        return convertToDtos(userEventService.findAll(), UserEventDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody
    UserEventDto get(@PathVariable( "id" ) Long id ) {
        UserEvent event = userEventService.findById(id);
        return RestPreconditions.checkFound(convertToDto(event, UserEventDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable( "id" ) Long id ) {
        try {
            userEventService.delete(id);
        } catch (UserEventNotFound userEventNotFound) {
            userEventNotFound.printStackTrace();
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public UserEventDto create( @RequestBody UserEventDto userEventDto ){
        Preconditions.checkNotNull( userEventDto );
        UserEvent userEvent = convertToEntity(userEventDto, UserEvent.class);
        UserEvent createdUserEvent = userEventService.create(userEvent);
        return convertToDto(createdUserEvent, UserEventDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long id, @RequestBody UserEventDto userEventDto) {
        UserEvent userEvent = convertToEntity(userEventDto, UserEvent.class);
        try {
            userEventService.update(userEvent);
        } catch (UserEventNotFound userEventNotFound) {
            userEventNotFound.printStackTrace();
        }
    }
}
