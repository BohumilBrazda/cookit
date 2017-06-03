package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.entity.RecipeItem;
import cz.brazda.cookit.repository.entity.exceptions.RecipeItemNotFound;
import cz.brazda.cookit.repository.RecipeItemRepository;
import cz.brazda.cookit.repository.service.RecipeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by virtual on 23.4.2017.
 */
@Service
public class RecipeItemServiceImpl extends RepositoryServiceImpl<RecipeItem, RecipeItemRepository, RecipeItemNotFound> implements RecipeItemService {

    @Autowired
    public RecipeItemServiceImpl(RecipeItemRepository recipeItemRepository){
        repository = recipeItemRepository;
    }


    @Override
    protected RecipeItemNotFound exception() {
        return new RecipeItemNotFound();
    }

    @Override
    protected void updateEntity(RecipeItem updatedElement, RecipeItem originEntity) {
        updatedElement.setAmount(originEntity.getAmount());
        updatedElement.setDescription(originEntity.getDescription());
        updatedElement.setName(originEntity.getName());
        updatedElement.setIngredient(originEntity.getIngredient());
        updatedElement.setUnit(originEntity.getUnit());
    }
}
