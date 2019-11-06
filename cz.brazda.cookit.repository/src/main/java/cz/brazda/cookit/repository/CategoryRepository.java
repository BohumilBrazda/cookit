package cz.brazda.cookit.repository;


import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
@Transactional
public interface CategoryRepository extends DtoProjectionRepository<Category, Long> {
}
