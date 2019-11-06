package cz.brazda.cookit.repository;


import cz.brazda.cookit.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
@Transactional
public interface AuthorRepository extends DtoProjectionRepository<Author, Long> {
}
