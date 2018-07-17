package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import cz.brazda.cookit.repository.service.RecipeService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by virtual on 29.4.2017.
 */
@RestController
@RequestMapping(value="/recipe")
public class RecipeController extends AbstractController<Recipe, RecipeDto> {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(ModelMapper modelMapper,  RecipeService recipeService) {
        super(modelMapper);
        this.recipeService = recipeService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<RecipeDto> findAll() {
        return convertToDtos(recipeService.findAll(), RecipeDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody RecipeDto findOne( @PathVariable( "id" ) Long id ) {
        Recipe recipe = recipeService.findById(id);
        return RestPreconditions.checkFound(convertToDto(recipe, RecipeDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus( HttpStatus.OK )
    public void delete( @PathVariable( "id" ) Long id ) {
        try {
            recipeService.delete(id);
        } catch (RecipeNotFound recipeNotFound) {
            recipeNotFound.printStackTrace();
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public Recipe create( @RequestBody Recipe resource ){
        Preconditions.checkNotNull( resource );
        return recipeService.create(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(@PathVariable("id") Long id, @RequestBody RecipeDto recipeDto) {
        Recipe recipe = convertToEntity(recipeDto, Recipe.class);
        try {
            Recipe updatedRecipe = recipeService.update(recipe);
            recipeService.update(updatedRecipe);
        } catch (RecipeNotFound recipeNotFound) {
            recipeNotFound.printStackTrace();
        }
    }
}