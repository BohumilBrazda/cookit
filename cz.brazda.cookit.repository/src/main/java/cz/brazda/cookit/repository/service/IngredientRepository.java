package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
