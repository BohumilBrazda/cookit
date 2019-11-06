package cz.brazda.cookit.repository;

import cz.brazda.cookit.repository.entity.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Repository
@Transactional(propagation= Propagation.REQUIRED)
public interface UserEventRepository extends DtoProjectionRepository<UserEvent, Long> {
}
