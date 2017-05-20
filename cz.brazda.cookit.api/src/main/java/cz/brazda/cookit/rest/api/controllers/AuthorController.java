package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.AuthorService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by virtual on 20.5.2017.
 */

@RestController
@RequestMapping(value="/author")
public class AuthorController extends AbstractController<Author, AuthorDto>{

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    AuthorDto get(@PathVariable( "id" ) Long id ) {
        Author author = authorService.findById(id);
        return RestPreconditions.checkFound(convertToDto(author, AuthorDto.class));
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public AuthorDto create( @RequestBody AuthorDto authorDto ){
        Preconditions.checkNotNull( authorDto );
        Author author = convertToEntity(authorDto, Author.class);
        AuthorDto createdAuthorDto  = convertToDto(authorService.create(author), AuthorDto.class);
        return createdAuthorDto;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAuthor(@RequestBody AuthorDto authorDto) {
        Author author = convertToEntity(authorDto, Author.class);
        try {
            Author updatedAuthor = authorService.update(author);
            authorService.update(updatedAuthor);
        } catch (AuthorNotFound authorNotFound) {
            authorNotFound.printStackTrace();
        }
    }
}
