package cz.brazda.cookit.repository.service.config;

import cz.brazda.cookit.repository.*;
import cz.brazda.cookit.repository.service.*;
import cz.brazda.cookit.repository.service.impl.*;
import cz.brazda.cookit.repository.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.content.jpa.config.EnableJpaStores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by virtual on 23.4.2017.
 */
@Configuration
@ComponentScan("cz.brazda.cookit.repository")
@ComponentScan("cz.brazda.cookit.store")
@EnableTransactionManagement
@Component
@EnableJpaStores
public class ServiceConfig {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl(authorRepository);
    }

    @Bean
    public RecipeService recipeService() {
        return new RecipeServiceImpl(recipeRepository, entityManagerFactory.getNativeEntityManagerFactory().createEntityManager());
    }

    @Bean
    public MealService mealService() {
        return new MealServiceImpl(mealRepository);
    }

    @Bean
    public UserEventService userEventService() {
        return new UserEventServiceImpl(userEventRepository);
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public ImageService imageService() {
        return new ImageServiceImpl(imageRepository);
    }

}
