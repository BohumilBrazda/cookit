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

import java.util.List;

/**
 * Created by virtual on 20.5.2017.
 */

@RestController
@RequestMapping(value="/author")
public class AuthorController extends BaseController<Author, AuthorDto>{

    @Autowired
    private AuthorService authorService;

    @RequestMapping( method = RequestMethod.GET )
    public @ResponseBody
    List<AuthorDto> findAll() {
        return convertToDtos(authorService.findAll(), AuthorDto.class);
    }

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
        return convertToDto(authorService.create(author), AuthorDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        Author author = convertToEntity(authorDto, Author.class);
        try {
            Author updatedAuthor = authorService.update(author);
            authorService.update(updatedAuthor);
        } catch (AuthorNotFound authorNotFound) {
            authorNotFound.printStackTrace();
        }
    }
}
