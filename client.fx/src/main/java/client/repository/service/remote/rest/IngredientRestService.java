package client.repository.service.remote.rest;

import client.repository.model.Ingredient;
import client.repository.model.UserEvent;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.common.dto.UserEventDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
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
public class IngredientRestService extends AbstractBaseRestService<Ingredient, IngredientDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/ingredient";

    @Autowired
    public IngredientRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    @Override
    public List<Ingredient> findAll() {
        try {
            return findAllEntities(IngredientDto.class, Ingredient.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all ingredients", e);
        }
    }

    @Override
    public Ingredient get(Long id) {
        try {
            return findEntity(id, IngredientDto.class,Ingredient.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find ingredient with id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            deleteEntity(id);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot delete ingredient with id " + id, e);
        }
    }

    @Override
    public Ingredient create(Ingredient event) {
        try {
            return createEntity(event, IngredientDto.class, Ingredient.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot create entity ingredient" + event.toString(), e);
        }
    }

    @Override
    public void update(Ingredient ingredient) {
        updateEntity(ingredient, IngredientDto.class);
    }

    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
