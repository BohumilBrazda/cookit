package client.repository.service.remote.rest;

import client.repository.model.Author;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import client.repository.service.remote.rest.converters.DtoToAuthorConverter;
import cz.brazda.cookit.common.dto.AuthorDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Bohumil Brázda on 22.5.2017.
 */
@Component
@Configurable(preConstruction = true)
public class AuthorRestService extends AbstractBaseRestService<Author, AuthorDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/author";

    @Autowired
    public AuthorRestService(List<Converter> converters, ModelMapper modelMapper, Client client) {
        super(converters, modelMapper, client);
    }

    /**
     *
     * @return all persisted authors in repository.
     */
    @Override
    public List<Author> findAll() {
        try {
//            List<Converter> converters = new ArrayList<>();
//            converters.add(new DtoToAuthorConverter());
            return findAllEntities(AuthorDto.class, Author.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all authors", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            deleteEntity(id);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot delete author with id " + id, e);
        }
    }

    @Override
    public Author get(Long id) {

        try {
            return findEntity(id, AuthorDto.class, Author.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find author with id ", e);
        }
    }

    /**
     * Persist new author to repository.
     * @param author client author details entity
     * @return persisted author entity with generated id
     */
    @Override
    public Author create(Author author) {
        try {
            return createEntity(author, AuthorDto.class, Author.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot createEntity author" + author.toString(), e);
        }
    }

    @Override
    public void update(Author author){
        updateEntity(author, AuthorDto.class);
    }


    @Override
    protected String getURIString() {
        return WS_URI;
    }
}
