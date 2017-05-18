package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;

/**
 * Created by virtual on 23.4.2017.
 */
public interface RecipeService extends RepositoryService<Recipe, RecipeNotFound>{
}
