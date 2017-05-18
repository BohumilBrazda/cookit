package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.AuthorRepository;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class AuthorServiceImpl extends RepositoryServiceImpl<Author, AuthorRepository, AuthorNotFound> implements AuthorService {

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        repository = authorRepository;
    }

    @Override
    protected AuthorNotFound createException() {
        return new AuthorNotFound();
    }


    @Override
    protected void updateEntity(Author updatedAuthor, Author originAuthor) {
        updatedAuthor.setFirstName(originAuthor.getFirstName());
        updatedAuthor.setSecondName(originAuthor.getSecondName());
    }
}
