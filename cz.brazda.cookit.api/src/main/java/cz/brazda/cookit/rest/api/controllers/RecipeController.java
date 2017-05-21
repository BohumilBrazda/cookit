package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import cz.brazda.cookit.repository.service.RecipeService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by virtual on 29.4.2017.
 */
@RestController
@RequestMapping(value="/recipe")
public class RecipeController extends BaseController<Recipe, RecipeDto>{

    @Autowired
    private RecipeService recipeService;

    @RequestMapping( method = RequestMethod.GET )
    public @ResponseBody List<RecipeDto> findAll() {
        return convertToDtos(recipeService.findAll(), RecipeDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody RecipeDto findOne( @PathVariable( "id" ) Long id ) {
        Recipe recipe = recipeService.findById(id);
        return RestPreconditions.checkFound(convertToDto(recipe, RecipeDto.class));
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Recipe create( @RequestBody Recipe resource ){
        Preconditions.checkNotNull( resource );
        return recipeService.create(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
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