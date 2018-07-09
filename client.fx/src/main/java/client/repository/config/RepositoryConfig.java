package client.repository.config;

import client.repository.service.remote.rest.*;
import client.repository.service.remote.rest.converters.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by virtual on 2.5.2017.
 */
@Configuration
@ComponentScan(basePackages = {"client.repository"}, basePackageClasses = AbstractBaseRestService.class)
public class RepositoryConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        //modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR);
        return modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public List<Converter> converters(){
        List<Converter> convertes = new ArrayList<>();
        convertes.add(new DtoToMealConverter());
        convertes.add(new DtoToRecipeConverter());
        convertes.add(new DtoToAuthorConverter());
        convertes.add(new DtoToUserEventConverter());
        convertes.add(new DtoToIngredientConverter());
        convertes.add(new RecipeToDtoConverter());
        convertes.add(new UserEventToDtoConverter());
        convertes.add(new AuthorToDtoConverter());
        convertes.add(new MealToDtoConverter());
        convertes.add(new IngredientToDtoConverter());
        convertes.add(new RecipeNodeToDtoConverter());
        return convertes;
    }
    @Bean
    public Client client(){
        return ClientBuilder.newBuilder().register(JsonProcessingFeature.class)
                .build();
    }

    @Bean
    public AuthorRestService authorRest(List<Converter> converters, ModelMapper modelMapper, Client client) {
        return new AuthorRestService(converters, modelMapper, client);

    }

    @Bean
    public MealRestService mealRest(List<Converter> converters, ModelMapper modelMapper, Client client) {
        return new MealRestService(converters, modelMapper, client);

    }
    @Bean
    public RecipeRestService recipeRest(List<Converter> converters, ModelMapper modelMapper, Client client) {
        return new RecipeRestService(converters, modelMapper, client);
    }

    @Bean
    public UserEventRestService userEventRest(List<Converter> converters, ModelMapper modelMapper, Client client) {
        return new UserEventRestService(converters, modelMapper, client);
    }

    @Bean
    public IngredientRestService ingredientRest(List<Converter> converters, ModelMapper modelMapper, Client client) {
        return new IngredientRestService(converters, modelMapper, client);
    }

    @Bean
    public UserEventToDtoConverter userEventToDtoConverter(){
        return new UserEventToDtoConverter();
    }

    @Bean
    public AuthorToDtoConverter authorToDtoConverter(){
        return new AuthorToDtoConverter();
    }

    @Bean
    public DtoToAuthorConverter dtoToAuthorConverter(){
        return new DtoToAuthorConverter();
    }
}
