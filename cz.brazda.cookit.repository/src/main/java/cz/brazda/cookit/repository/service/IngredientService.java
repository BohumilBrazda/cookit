package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.entity.exceptions.IngredientNotFound;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Transactional
public interface IngredientService extends RepositoryService<Ingredient, IngredientNotFound>{

}
