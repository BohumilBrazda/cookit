package cz.brazda.cookit.rest.api.controllers;

import com.google.common.base.Preconditions;
import cz.brazda.cookit.common.dto.MealDto;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFound;
import cz.brazda.cookit.repository.service.MealService;
import cz.brazda.cookit.rest.api.utils.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bohumil Brázda on 21.5.2017.
 */
@RestController
@RequestMapping(value="/meal")
public class MealController extends BaseController<Meal, MealDto> {

    @Autowired
    private MealService mealService;

    @RequestMapping( method = RequestMethod.GET )
    public @ResponseBody
    List<MealDto> findAll() {
        return convertToDtos(mealService.findAll(), MealDto.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody MealDto findOne( @PathVariable( "id" ) Long id ) {
        Meal meal = mealService.findById(id);
        return RestPreconditions.checkFound(convertToDto(meal, MealDto.class));
    }

    @RequestMapping( method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.CREATED )
    @ResponseBody
    public Meal create( @RequestBody Meal resource ){
        Preconditions.checkNotNull( resource );
        return mealService.create(resource);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody MealDto mealDto) {
        Meal meal = convertToEntity(mealDto, Meal.class);
        try {
            Meal updatedMeal = mealService.update(meal);
            mealService.update(updatedMeal);
        } catch (MealNotFound mealNotFound) {
            mealNotFound.printStackTrace();
        }
    }
}
