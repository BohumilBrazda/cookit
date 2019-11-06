package cz.brazda.cookit.repository.service;



import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 22.4.2017.
 */
@Transactional
public interface AuthorService extends RepositoryService<Author, AuthorNotFound>{

}
