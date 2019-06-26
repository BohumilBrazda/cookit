package cz.brazda.cookit.repository.service.config;

import cz.brazda.cookit.repository.AuthorRepository;
import cz.brazda.cookit.repository.MealRepository;
import cz.brazda.cookit.repository.RecipeRepository;
import cz.brazda.cookit.repository.UserEventRepository;
import cz.brazda.cookit.repository.service.AuthorService;
import cz.brazda.cookit.repository.service.MealService;
import cz.brazda.cookit.repository.service.RecipeService;
import cz.brazda.cookit.repository.service.UserEventService;
import cz.brazda.cookit.repository.service.impl.AuthorServiceImpl;
import cz.brazda.cookit.repository.service.impl.MealServiceImpl;
import cz.brazda.cookit.repository.service.impl.RecipeServiceImpl;
import cz.brazda.cookit.repository.service.impl.UserEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * Created by virtual on 23.4.2017.
 */
@Configuration
@ComponentScan("cz.brazda.cookit.repository")
@Component
public class ServiceConfig {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserEventRepository userEventRepository;

    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl(authorRepository);
    }

    @Bean
    public RecipeService recipeService() {
        return new RecipeServiceImpl(recipeRepository);
    }

    @Bean
    public MealService mealService() {
        return new MealServiceImpl(mealRepository);
    }

    @Bean
    public UserEventService userEventService() {
        return new UserEventServiceImpl(userEventRepository);
    }
}
