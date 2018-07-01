package client.repository.service.remote.rest;

import client.repository.model.Meal;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import cz.brazda.cookit.common.dto.MealDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.List;


/**
 * Created by Bohumil Brázda on 22.5.2017.
 */
@Component
@Configurable(preConstruction = true)
public class MealRestService extends AbstractBaseRestService<Meal, MealDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/meal";

    @Autowired
    public MealRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    /**
     *
     * @return all persisted authors in repository.
     */
    @Override
    public List<Meal> findAll() {
        try {
            return findAllEntities(MealDto.class, Meal.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all meals", e);
        }
    }

    @Override
    public Meal get(Long id) {
        try {
            return findEntity(id, MealDto.class, Meal.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find meal with id: " + id, e);
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

    /**
     * Persist new meal to repository.
     * @param meal client meal details entity
     * @return persisted meal entity with generated id
     */
    @Override
    public Meal create(Meal meal) {
        try {
            return createEntity(meal, MealDto.class, Meal.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot createEntity meal" + meal.toString(), e);
        }
    }

    @Override
    public void update(Meal meal){
        updateEntity(meal, MealDto.class);
    }


    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
