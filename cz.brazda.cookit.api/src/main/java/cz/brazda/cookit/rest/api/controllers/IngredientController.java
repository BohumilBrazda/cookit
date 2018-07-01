package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.entity.exceptions.IngredientNotFound;
import cz.brazda.cookit.repository.service.IngredientService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Bohumil Brázda on 14.5.2017.
 */
@RestController
@RequestMapping(value="/ingredient")
public class IngredientController extends AbstractController<Ingredient, IngredientDto> {

    private IngredientService ingredientService;

    @Autowired
    public IngredientController(ModelMapper modelMapper, IngredientService ingredientService) {
        super(modelMapper);
        this.ingredientService = ingredientService;
    }

    @RequestMapping( method = RequestMethod.GET )
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    List<IngredientDto> findAll() {
        return convertToDtos(ingredientService.findAll(), IngredientDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus( HttpStatus.OK )
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody
    IngredientDto findOne(@PathVariable( "id" ) Long id ) {
        Ingredient ingredient = ingredientService.findById(id);
        return RestPreconditions.checkFound(convertToDto(ingredient, IngredientDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus( HttpStatus.OK )
    public void delete(@PathVariable( "id" ) Long id ) {
        try {
            ingredientService.delete(id);
        } catch (IngredientNotFound ingredientNotFound) {
            ingredientNotFound.printStackTrace();
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseBody
    public Ingredient create( @RequestBody Ingredient resource ){
        Preconditions.checkNotNull( resource );
        return ingredientService.create(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Consumes(MediaType.APPLICATION_JSON)
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
