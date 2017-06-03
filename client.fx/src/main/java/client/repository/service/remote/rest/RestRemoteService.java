package client.repository.service.remote.rest;

import client.repository.model.Entity;

import java.util.List;

/**
 * CRUD operations intarface
 * Created by Bohumil Brázda on 25.5.2017.
 */
public interface RestRemoteService<T extends Entity> {

    List<T> findAll();

    T get(Long id );

    void delete(Long id );

    T create(T entity);

    void update(T entity);


}
