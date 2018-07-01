package client.repository.service.remote.rest;

import client.repository.model.Recipe;
import client.repository.model.RecipeItem;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import com.fasterxml.jackson.databind.JsonNode;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.common.dto.RecipeItemDto;
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
public class RecipeItemRestService extends AbstractBaseRestService<RecipeItem, RecipeItemDto> {

    private static final String WS_URI = "http://localhost:8080/cookit//RecipeItem";

    @Autowired
    public RecipeItemRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    @Override
    public List<RecipeItem> findAll() {
        try {
            return findAllEntities(RecipeItemDto.class,RecipeItem.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all recipe items", e);
        }
    }

    @Override
    public RecipeItem get(Long id) {
        try {
            return findEntity(id, RecipeItemDto.class, RecipeItem.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find recipe item with id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            deleteEntity(id);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot delete recipe item with id " + id, e);
        }
    }

    @Override
    public RecipeItem create(RecipeItem recipe) {
        try {
            return createEntity(recipe, RecipeItemDto.class,RecipeItem.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot createEntity recipe item" + recipe.toString(), e);
        }
    }

    @Override
    public void update(RecipeItem recipeItem) {
        updateEntity(recipeItem, RecipeItemDto.class);
    }

    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
