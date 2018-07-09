package cz.brazda.cookit.rest.config;

import cz.brazda.cookit.rest.api.controllers.converter.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by virtual on 2.5.2017.
 */
@Configuration
@ComponentScan(basePackages = "cz.brazda.cookit.rest.api", basePackageClasses = AbstractController.class)
public class ApiConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD).setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        List<Converter> converters = new ArrayList<>(asList());
        converters.add(dtoToAuthorConverter());
        converters.add(dtoToMealConverter());
        converters.add(dtoToIngredientConverter());
        converters.add(dtoToMealConverter());
        converters.add(dtoToRecipeItemConverter());
        converters.add(dtoToRecipeConverter());
        converters.add(authorToDtoConverter());
        converters.add(dtoToAuthorConverter());
        converters.add(ingredientToDtoConverter());
        converters.add(mealToDtoConverter());
        converters.add(userEventToDtoConverter());
        converters.add(recipeItemToDtoConverter());
        converters.add(recipeToDtoConverter());

        converters.forEach(converter -> modelMapper.addConverter(converter));

        return modelMapper;
    }

    @Bean
    public DtoToAuthorConverter dtoToAuthorConverter() {
        return new DtoToAuthorConverter();
    }

    @Bean
    public AuthorToDtoConverter authorToDtoConverter() {
        return new AuthorToDtoConverter();
    }

    @Bean
    public DtoToUserEventConverter dtoToUserEventConverter() {
        return new DtoToUserEventConverter();
    }

    @Bean
    public UserEventToDtoConverter userEventToDtoConverter() {
        return new UserEventToDtoConverter();
    }

    @Bean
    public MealToDtoConverter mealToDtoConverter() {
        return new MealToDtoConverter();
    }

    @Bean
    public DtoToMealConverter dtoToMealConverter() {
        return new DtoToMealConverter();
    }

    @Bean
    public DtoToIngredientConverter dtoToIngredientConverter() {
        return new DtoToIngredientConverter();
    }

    @Bean
    public IngredientToDtoConverter ingredientToDtoConverter() {
        return new IngredientToDtoConverter();
    }

    @Bean
    public RecipeItemToDtoConverter recipeItemToDtoConverter() {
        return new RecipeItemToDtoConverter();
    }
    @Bean
    public DtoToRecipeItemConverter dtoToRecipeItemConverter() {
        return new DtoToRecipeItemConverter();
    }

    @Bean
    public DtoToRecipeConverter dtoToRecipeConverter() {
        return new DtoToRecipeConverter();
    }

    @Bean
    public RecipeToDtoConverter recipeToDtoConverter() {
        return new RecipeToDtoConverter();
    }
}
