package client.repository.service.remote.rest;

import client.repository.model.Author;
import client.repository.service.remote.exceptions.RepositoryServiceRemoteException;
import cz.brazda.cookit.common.dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.util.List;


/**
 * Created by Bohumil Brázda on 22.5.2017.
 */
@Component
public class AuthorRestService extends AbstractBaseRestService<Author, AuthorDto> {

    private static final String WS_URI = "http://localhost:8080/cookit/author";

    public AuthorRestService(ModelMapper modelMapper, Client client) {
        super(modelMapper, client);
    }

    /**
     *
     * @return all persisted authors in repository.
     */
    @Override
    public List<Author> findAll() {
        try {
            return findAllEntities(AuthorDto.class, Author.class);
        } catch (IOException e) {
            throw new RepositoryServiceRemoteException("Cannot find all authors", e);
        }
    }

    @Override
    public Author get(Long id) {

        return null;
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
