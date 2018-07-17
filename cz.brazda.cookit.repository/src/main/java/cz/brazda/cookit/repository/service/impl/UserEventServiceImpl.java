package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.UserEventRepository;
import cz.brazda.cookit.repository.entity.UserEvent;
import cz.brazda.cookit.repository.entity.exceptions.UserEventNotFound;
import cz.brazda.cookit.repository.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class UserEventServiceImpl extends RepositoryServiceImpl<UserEvent, UserEventRepository, UserEventNotFound> implements UserEventService {

    @Autowired
    public UserEventServiceImpl(UserEventRepository userEventRepository){
        repository = userEventRepository;
        exception = new UserEventNotFound();
    }

    @Override
    protected void updateEntity(UserEvent updatedElement, UserEvent originEntity) {
        updatedElement.setEventTime(originEntity.getEventTime());
        updatedElement.setAuthor(originEntity.getAuthor());
    }
}
