package cz.brazda.cookit.repository.service;


import cz.brazda.cookit.repository.entity.RecipeItem;
import cz.brazda.cookit.repository.entity.exceptions.RecipeItemNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by virtual on 23.4.2017.
 */
@Transactional
public interface RecipeItemService extends RepositoryService<RecipeItem, RecipeItemNotFound>{
}
