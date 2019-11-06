package cz.brazda.cookit.repository.service;

import cz.brazda.cookit.repository.entity.Category;
import cz.brazda.cookit.repository.entity.exceptions.AuthorNotFound;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryService extends RepositoryService<Category, AuthorNotFound>{
}
