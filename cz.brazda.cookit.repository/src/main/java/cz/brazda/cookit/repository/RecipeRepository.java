package cz.brazda.cookit.repository;


import cz.brazda.cookit.repository.entity.Recipe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
@Transactional
public interface RecipeRepository extends DtoProjectionRepository<Recipe, Long> {
}
