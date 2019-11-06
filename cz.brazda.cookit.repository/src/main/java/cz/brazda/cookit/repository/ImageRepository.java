package cz.brazda.cookit.repository;


import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
@Transactional
public interface ImageRepository extends DtoProjectionRepository<Image, Long> {
}
