package cz.brazda.cookit.repository.service.impl;

import cz.brazda.cookit.repository.MealRepository;
import cz.brazda.cookit.repository.entity.Meal;
import cz.brazda.cookit.repository.entity.exceptions.MealNotFound;
import cz.brazda.cookit.repository.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class MealServiceImpl extends RepositoryServiceImpl<Meal, MealRepository, MealNotFound> implements MealService {

    @Autowired
    public MealServiceImpl(MealRepository mealRepository){
        repository = mealRepository;
    }

    @Override
    protected MealNotFound exception() {
        return new MealNotFound();
    }

    @Override
    protected void updateEntity(Meal updatedElement, Meal originEntity) {
        updatedElement.setName(originEntity.getName());
        updatedElement.setDescription(originEntity.getDescription());
        updatedElement.setPictures(originEntity.getPictures());
        updatedElement.setRecipes(originEntity.getRecipes());
    }

    @Override
    public List<Meal> findAll() {
        return super.findAll();
    }
}
