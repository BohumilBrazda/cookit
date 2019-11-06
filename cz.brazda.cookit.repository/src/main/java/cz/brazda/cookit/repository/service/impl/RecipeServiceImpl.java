package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.SimpleRecipe;
import cz.brazda.cookit.repository.AuthorRepository;
import cz.brazda.cookit.repository.RecipeRepository;
import cz.brazda.cookit.repository.UserEventRepository;
import cz.brazda.cookit.repository.entity.Recipe;
import cz.brazda.cookit.repository.entity.RecipeItem;
import cz.brazda.cookit.repository.entity.UserEvent;
import cz.brazda.cookit.repository.entity.exceptions.RecipeNotFound;
import cz.brazda.cookit.repository.service.RecipeItemService;
import cz.brazda.cookit.repository.service.RecipeService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by virtual on 23.4.2017.
 */
@Service
@Transactional
public class RecipeServiceImpl extends RepositoryServiceImpl<Recipe, RecipeRepository, RecipeNotFound> implements RecipeService {

    @Autowired
    RecipeItemService recipeItemService;
    @Autowired
    UserEventRepository userEventRepository;



    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, EntityManager entityManager){
        this.repository = recipeRepository;

        this.em = em;
        exception = new RecipeNotFound();
    }

    @Override
    public Recipe create(Recipe entity) {
        List<RecipeItem> items = new ArrayList<>(entity.getItems());
        entity.getItems().clear();
        Recipe recipe = repository.save(entity);

        for (RecipeItem item:items) {
            item.setRecipe(recipe);
            RecipeItem savedItem = recipeItemService.create(item);
            recipe.getItems().add(savedItem);
        }

        repository.save(recipe);
        return recipe;
    }

    @Override
    protected void updateEntity(Recipe updatedElement, Recipe originEntity) {
        UserEvent edited = userEventRepository.save(updatedElement.getEdited());
        updatedElement.setEdited(edited);
        List<RecipeItem> savedItems = new ArrayList<>();

        for (RecipeItem item :updatedElement.getItems() ){
            savedItems.add(recipeItemService.create(item));
        }
        updatedElement.getItems().clear();
        updatedElement.getItems().addAll(savedItems);

        updatedElement.setCreated(originEntity.getEdited());
        updatedElement.setMeal(originEntity.getMeal());
        updatedElement.setName(originEntity.getName());
        //updatedElement.setCategories(originEntity.getCategories());
        updatedElement.setNumberOfPortion(originEntity.getNumberOfPortion());
        updatedElement.setPrice(originEntity.getPrice());
        if(!Hibernate. isInitialized(updatedElement)){
            Hibernate.initialize(updatedElement);
        }
        repository.save(updatedElement);
    }

    @Override
    public Recipe fetchLazy(Long id) {
        Recipe recipe = findById(id);
        if(!Hibernate.isInitialized(recipe.getItems())){
            Hibernate.initialize(recipe.getItems());
        }
        return recipe;
    }
}