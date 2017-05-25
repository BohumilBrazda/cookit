package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.entity.exceptions.IngredientNotFound;
import cz.brazda.cookit.repository.service.IngredientService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bohumil Brázda on 14.5.2017.
 */
@RestController
@RequestMapping(value="/ingredient")
public class IngredientController extends AbstractController<Ingredient, IngredientDto> {

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping( method = RequestMethod.GET )
    public @ResponseBody
    List<IngredientDto> findAll() {
        return convertToDtos(ingredientService.findAll(), IngredientDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    IngredientDto findOne(@PathVariable( "id" ) Long id ) {
        Ingredient ingredient = ingredientService.findById(id);
        return RestPreconditions.checkFound(convertToDto(ingredient, IngredientDto.class));
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Ingredient create( @RequestBody Ingredient resource ){
        Preconditions.checkNotNull( resource );
        return ingredientService.create(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody IngredientDto ingredientDto) {
        Ingredient ingredient = convertToEntity(ingredientDto, Ingredient.class);
        try {
            Ingredient updated = ingredientService.update(ingredient);
            ingredientService.update(updated);
        } catch (IngredientNotFound ingredientNotFound) {
            ingredientNotFound.printStackTrace();
        }
    }
}
