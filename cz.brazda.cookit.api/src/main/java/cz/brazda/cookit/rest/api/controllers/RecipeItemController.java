package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.RecipeItemDto;
import cz.brazda.cookit.repository.entity.RecipeItem;
import cz.brazda.cookit.repository.entity.exceptions.RecipeItemNotFound;
import cz.brazda.cookit.repository.service.RecipeItemService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by virtual on 29.4.2017.
 */
@RestController
@RequestMapping(value="/RecipeItem")
public class RecipeItemController extends AbstractController<RecipeItem, RecipeItemDto> {

    private RecipeItemService recipeItemService;

    @Autowired
    public RecipeItemController(ModelMapper modelMapper, RecipeItemService RecipeItemService) {
        super(modelMapper);
        this.recipeItemService = RecipeItemService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<RecipeItemDto> findAll() {
        return convertToDtos(recipeItemService.findAll(), RecipeItemDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody RecipeItemDto findOne( @PathVariable( "id" ) Long id ) {
        RecipeItem RecipeItem = recipeItemService.findById(id);
        return RestPreconditions.checkFound(convertToDto(RecipeItem, RecipeItemDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus( HttpStatus.OK )
    public void delete( @PathVariable( "id" ) Long id ) {
        try {
            recipeItemService.delete(id);
        } catch (RecipeItemNotFound RecipeItemNotFound) {
            RecipeItemNotFound.printStackTrace();
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public RecipeItemDto create( @RequestBody RecipeItemDto recipeItemDto ){
        Preconditions.checkNotNull( recipeItemDto );
        RecipeItem item = convertToEntity(recipeItemDto, RecipeItem.class);
        return convertToDto(recipeItemService.create(item), RecipeItemDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathVariable("id") Long id, @RequestBody RecipeItemDto RecipeItemDto) {
        RecipeItem RecipeItem = convertToEntity(RecipeItemDto, RecipeItem.class);
        try {
            RecipeItem updatedRecipeItem = recipeItemService.update(RecipeItem);
            recipeItemService.update(updatedRecipeItem);
        } catch (RecipeItemNotFound RecipeItemNotFound) {
            RecipeItemNotFound.printStackTrace();
        }
    }
}