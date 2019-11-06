package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFound;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 23.4.2017.
 */
@Transactional
public interface MealService extends RepositoryService<Meal, MealNotFound>{
    Meal fetchLazyImages(Long id);
}
