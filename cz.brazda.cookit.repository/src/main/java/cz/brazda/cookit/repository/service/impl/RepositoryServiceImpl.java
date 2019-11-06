package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.repository.DtoProjectionRepository;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RepositoryException;
import cz.brazda.cookit.repository.service.RepositoryService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
@Transactional
public abstract class RepositoryServiceImpl<T extends IdElement, U extends DtoProjectionRepository<T, Long>, V extends RepositoryException > implements RepositoryService<T, V> {

    protected U repository;

    @Autowired
    protected EntityManager em;

    /**
     * Create new instance of concrete exception
     * @return new instance of exception
     */
    protected V exception;

    /**
     * Define details attributes from updated entity
     *
     * @param updatedElement entity to update
     */
    @Transactional
    protected abstract void updateEntity(T updatedElement, T originEntity);

    @Override
    @Transactional
    public T create(T entity) {
        T createdEntity = entity;
        return repository.save(createdEntity);
    }

    @Override
    @Transactional(rollbackFor = RepositoryException.class)
    public void delete(Long id) throws V {
        T deletedEntity = findById(id);
        if(deletedEntity == null){
            throw exception;
        }
        repository.delete(deletedEntity);
    }

    @Override
    @Transactional(rollbackFor = RepositoryException.class)
    public T update(T entity) throws V {
        T updatedEntity = findById(entity.getId());
        if(updatedEntity == null){
            throw exception;
        }
        updateEntity(entity, updatedEntity);
        return updatedEntity;
    }

    @Override
    @Transactional
    public T findById(Long id) {
        Assert.notNull(id == null, "Id cannot be null!");
        Session session = em.unwrap(org.hibernate.Session.class);
        return (T)session.merge(repository.findById(id).get());
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public <V> V findById(Long id, Class<V> type) {
        T entity = repository.findById(id).get();

        T mergedEntity = em.merge(entity);
        V recipe = repository.findById(id, type);
        return recipe;
    }




}
