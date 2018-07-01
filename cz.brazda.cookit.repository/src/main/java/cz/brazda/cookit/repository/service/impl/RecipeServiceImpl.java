package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.RecipeRepository;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import cz.brazda.cookit.repository.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class RecipeServiceImpl extends RepositoryServiceImpl<Recipe, RecipeRepository, RecipeNotFound> implements RecipeService {

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository){
        repository = recipeRepository;
    }


    @Override
    protected RecipeNotFound exception() {
        return new RecipeNotFound();
    }

    @Override
    protected void updateEntity(Recipe updatedElement, Recipe originEntity) {
        updatedElement.setEdited(originEntity.getEdited());
        updatedElement.setMeal(originEntity.getMeal());
        updatedElement.setName(originEntity.getName());
        //updatedElement.setCategories(originEntity.getCategories());
        updatedElement.setNumberOfPortion(originEntity.getNumberOfPortion());
        updatedElement.setPrice(originEntity.getPrice());
    }
}
