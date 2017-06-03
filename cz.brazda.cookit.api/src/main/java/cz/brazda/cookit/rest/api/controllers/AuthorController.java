package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.AuthorDto;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.AuthorService;
import cz.brazda.cookit.rest.api.controllers.converter.DtoToAuthorConvertor;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by virtual on 20.5.2017.
 */

@RestController
@RequestMapping(value="/author")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorController extends AbstractController<Author, AuthorDto> {

    @Autowired
    private AuthorService authorService;

    @RequestMapping( method = RequestMethod.GET )
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody
    List<AuthorDto> findAll() {
        return convertToDtos(authorService.findAll(), AuthorDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody
    AuthorDto get(@PathVariable( "id" ) Long id ) {
        Author author = authorService.findById(id);
        return RestPreconditions.checkFound(convertToDto(author, AuthorDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable( "id" ) Long id ) {
        try {
            authorService.delete(id);
        } catch (AuthorNotFound authorNotFound) {
            authorNotFound.printStackTrace();
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public AuthorDto create( @RequestBody AuthorDto authorDto ){
        Preconditions.checkNotNull( authorDto );
        Author author = convertToEntity(authorDto, Author.class);
        return convertToDto(authorService.create(author), AuthorDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        Author author = convertToEntity(new DtoToAuthorConvertor(), authorDto, Author.class);
        try {
            authorService.update(author);
        } catch (AuthorNotFound authorNotFound) {
            authorNotFound.printStackTrace();
        }
    }
}
