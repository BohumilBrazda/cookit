package cz.brazda.cookit.repository.service.impl;


import cz.brazda.cookit.repository.AuthorRepository;
import cz.brazda.cookit.repository.CategoryRepository;
import cz.brazda.cookit.repository.entity.Author;
import cz.brazda.cookit.repository.entity.Category;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import cz.brazda.cookit.repository.service.AuthorService;
import cz.brazda.cookit.repository.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by virtual on 23.4.2017.
 */
@Service
@Transactional
public class CategoryServiceImpl extends RepositoryServiceImpl<Category, CategoryRepository, AuthorNotFound> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        repository = categoryRepository;
        exception = new AuthorNotFound();
    }

    @Override
    protected void updateEntity(Category updatedAuthor, Category originAuthor) {
        updatedAuthor.setCategoryName(originAuthor.getCategoryName());
        updatedAuthor.setDescription(originAuthor.getDescription());
    }

    @Override
    public <V> V findById(Long id, Class<V> type) {
        return repository.findById(id, type);
    }


}
