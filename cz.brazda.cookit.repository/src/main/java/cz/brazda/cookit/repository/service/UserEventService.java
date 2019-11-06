package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.UserEvent;
import cz.brazda.cookit.repository.entity.exceptions.UserEventNotFound;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 23.4.2017.
 */
@Transactional
public interface UserEventService extends RepositoryService<UserEvent, UserEventNotFound>{
}
