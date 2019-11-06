package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 23.4.2017.
 */

@Transactional
public interface RecipeService extends RepositoryService<Recipe, RecipeNotFound>{

    Recipe fetchLazy(Long id);
}
