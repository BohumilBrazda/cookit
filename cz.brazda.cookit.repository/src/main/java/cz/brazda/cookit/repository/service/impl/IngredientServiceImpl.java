package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.entity.Ingredient;
import cz.brazda.cookit.repository.entity.exceptions.IngredientNotFound;
import cz.brazda.cookit.repository.IngredientRepository;
import cz.brazda.cookit.repository.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
@Transactional
public class IngredientServiceImpl extends RepositoryServiceImpl<Ingredient, IngredientRepository, IngredientNotFound> implements IngredientService {

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository){
        repository = ingredientRepository;exception = new IngredientNotFound();
    }

//    @Override
//    protected IngredientNotFound exception() {
//        return new IngredientNotFound();
//    }


    @Override
    protected void updateEntity(Ingredient updatedIngredient, Ingredient originIngredient) {
        updatedIngredient.setName(originIngredient.getName());
        updatedIngredient.setDescription(originIngredient.getDescription());
    }
}
