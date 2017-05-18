package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.repository.entity.exceptions.RepositoryException;

import java.util.List;

/**
 * Created by virtual on 23.4.2017.
 */
public interface RepositoryService<T extends IdElement, U extends RepositoryException> {

    public T create(T entity);

    public T update(T entity) throws U;

    public T delete(Long id) throws U;

    public List<T> findAll();

    public T findById(Long id);
}
