package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.repository.entity.exceptions.RepositoryException;

import java.util.List;

/**
 * Created by virtual on 23.4.2017.
 */

public interface RepositoryService<T extends IdElement, U extends RepositoryException> {

    T create(T entity);

    T update(T entity) throws U;

    void delete(Long id) throws U;

    List<T> findAll();

    T findById(Long id);
}
