package client.repository.service.remote.rest;

import client.repository.model.Recipe;
import client.repository.model.UserEvent;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import com.fasterxml.jackson.databind.JsonNode;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bohumil Brázda on 9.7.2017.
 */
@Component
@Configurable(preConstruction = true)
public class UserEventRestService extends AbstractBaseRestService<UserEvent, UserEventDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/userEvent";

    @Autowired
    public UserEventRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    @Override
    public List<UserEvent> findAll() {
        try {
            return findAllEntities(UserEventDto.class, UserEvent.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all user events", e);
        }
    }

    @Override
    public UserEvent get(Long id) {
        try {
            return findEntity(id, UserEventDto.class, UserEvent.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find user event with id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            deleteEntity(id);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot delete user event with id " + id, e);
        }
    }

    @Override
    public UserEvent create(UserEvent event) {
        try {
            return createEntity(event, UserEventDto.class, UserEvent.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot createEntity userEvent" + event.toString(), e);
        }
    }

    @Override
    public void update(UserEvent event) {
        updateEntity(event, UserEventDto.class);
    }

    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
