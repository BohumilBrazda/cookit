package client.fx.config;

import client.fx.controllers.AuthorsController;
import client.fx.controllers.MealsController;
import client.fx.controllers.RecipesController;
import client.repository.config.RepositoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Main JavaFX Client application configuration class
 * Created by Bohumil Brázda on 22.5.2017.
 */
@Configuration
@Import({RepositoryConfig.class})
@ComponentScan("client")
public class ClientApplConfig {

    @Bean
    MealsController mealsController() {
        return new MealsController();
    }

    @Bean
    AuthorsController authorsController() {
        return new AuthorsController();
    }

    @Bean
    RecipesController recipesController() {
        return new RecipesController();
    }

}
