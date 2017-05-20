package cz.brazda.cookit.repository.service.impl;

import cz.brazda.cookit.repository.MealRepository;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFoud;
import cz.brazda.cookit.repository.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class MealServiceImpl extends RepositoryServiceImpl<Meal, MealRepository, MealNotFoud> implements MealService {

    @Autowired
    public MealServiceImpl(MealRepository mealRepository){
        repository = mealRepository;
    }

    @Override
    protected MealNotFoud createException() {
        return new MealNotFoud();
    }

    @Override
    protected void updateEntity(Meal updatedElement, Meal originEntity) {
        updatedElement.setName(originEntity.getName());
        updatedElement.setDescription(originEntity.getDescription());
        updatedElement.setPictures(originEntity.getPictures());
        updatedElement.setRecipes(originEntity.getRecipes());
    }
}