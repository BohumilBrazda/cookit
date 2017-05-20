package cz.brazda.cookit.rest.api.controllers;

import cz.brazda.cookit.common.dto.IngredientDto;
import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by virtual on 14.5.2017.
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
}
