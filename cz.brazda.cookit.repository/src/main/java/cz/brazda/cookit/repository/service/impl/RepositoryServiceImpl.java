package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.common.IdElement;
import cz.brazda.cookit.repository.entity.exceptions.RepositoryException;
import cz.brazda.cookit.repository.service.RepositoryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
public abstract class RepositoryServiceImpl<T extends IdElement, U extends JpaRepository<T, Long>, V extends RepositoryException > implements RepositoryService<T, V> {

    protected U repository;

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
    protected abstract void updateEntity(T updatedElement, T originEntity);

    @Override
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
        updateEntity(updatedEntity, entity);
        return updatedEntity;
    }

    @Override
    @Transactional
    public T findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }
}
