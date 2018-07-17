package cz.brazda.cookit.repository.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



/**
 * Created by virtual on 23.4.2017.
 */
@Configuration
@ComponentScan("cz.brazda.cookit.repository")
public class ServiceConfig {

//    @Autowired
//    private AuthorRepository authorRepository;
//    @Autowired
//    private MealRepository mealRepository;
//    @Autowired
//    private RecipeRepository recipeRepository;
//    @Autowired
//    private UserEventRepository userEventRepository;
//
//    @Bean
//    public AuthorService authorService() {
//        return new AuthorServiceImpl(authorRepository);
//    }
//
//    @Bean
//    public RecipeService recipeService() {
//        return new RecipeServiceImpl(recipeRepository);
//    }
//
//    @Bean
//    public MealService mealService() {
//        return new MealServiceImpl(mealRepository);
//    }
//
//    @Bean
//    public UserEventService userEventService() {
//        return new UserEventServiceImpl(userEventRepository);
//    }
}
