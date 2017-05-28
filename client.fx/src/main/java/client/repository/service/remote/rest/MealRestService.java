package client.repository.service.remote.rest;

import client.repository.model.Author;
import client.repository.model.Meal;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import client.repository.service.remote.rest.converters.DtoToMealConverter;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.common.dto.MealDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.List;


/**
 * Created by Bohumil Brázda on 22.5.2017.
 */
@Component
public class MealRestService extends AbstractBaseRestService<Meal, MealDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/meal";

    public MealRestService(ModelMapper modelMapper, Client client) {
        super(modelMapper, client);
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
            throw new RepositoryServiceRemoteException("Cannot find all authors", e);
        }
    }

    @Override
    public Meal get(Long id) {

        return null;
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
