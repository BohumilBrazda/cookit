package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.RecipeDto;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.service.RecipeService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virtual on 29.4.2017.
 */
@RestController
@RequestMapping(value="/recipe")
public class RecipeController extends AbstractController<Recipe, RecipeDto>{

    @Autowired
    private RecipeService recipeService;

    @RequestMapping( method = RequestMethod.GET )
    public @ResponseBody List<RecipeDto> findAll() {
        return toDtos(recipeService.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody RecipeDto findOne( @PathVariable( "id" ) Long id ) {
        Recipe recipe = recipeService.findById(id);
        return RestPreconditions.checkFound(toDto(recipe));
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Recipe create( @RequestBody Recipe resource ){
        Preconditions.checkNotNull( resource );
        return recipeService.create(resource);
    }

    @Override
    public RecipeDto toDto(Recipe recipe){
        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
        return recipeDto;
    }
}