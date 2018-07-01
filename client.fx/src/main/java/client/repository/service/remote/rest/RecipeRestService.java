package client.repository.service.remote.rest;

import client.repository.model.Recipe;
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
public class RecipeRestService extends AbstractBaseRestService<Recipe, RecipeDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/recipe";

    @Autowired
    public RecipeRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    @Override
    public List<Recipe> findAll() {
        try {

            return findAllEntities(RecipeDto.class, Recipe.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all recipes", e);
        }
    }

    @Override
    public Recipe get(Long id) {
        try {
            return findEntity(id, RecipeDto.class, Recipe.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find recipe with id: " + id, e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            deleteEntity(id);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot delete meal with id " + id, e);
        }
    }

    @Override
    public Recipe create(Recipe recipe) {
        try {
            return createEntity(recipe, RecipeDto.class, Recipe.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot createEntity meal" + recipe.toString(), e);
        }
    }

    @Override
    public void update(Recipe recipe) {
        updateEntity(recipe, RecipeDto.class);
    }

    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
