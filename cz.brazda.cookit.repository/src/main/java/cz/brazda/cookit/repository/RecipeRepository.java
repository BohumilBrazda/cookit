package cz.brazda.cookit.repository;


import cz.brazda.cookit.repository.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
