package client.repository.config;

import client.repository.service.remote.rest.AbstractBaseRestService;
import client.repository.service.remote.rest.AuthorRestService;
import client.repository.service.remote.rest.MealRestService;
import client.repository.service.remote.rest.RecipeRestService;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * Created by virtual on 2.5.2017.
 */
@Configuration
@ComponentScan(basePackages = {"client.repository"}, basePackageClasses = AbstractBaseRestService.class)
public class RepositoryConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader());
        modelMapper.getConfiguration().setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        return modelMapper;
    }

    @Bean
    public Client client(){
        return ClientBuilder.newBuilder().register(JsonProcessingFeature.class)
                .property(JsonGenerator.PRETTY_PRINTING, true).build();
    }

    @Bean
    public AuthorRestService authorRest(ModelMapper modelMapper, Client client) {
        return new AuthorRestService(modelMapper, client);

    }
    @Bean
    public MealRestService mealRest(ModelMapper modelMapper, Client client) {
        return new MealRestService(modelMapper, client);

    }
    @Bean
    public RecipeRestService recipeRest(ModelMapper modelMapper, Client client) {
        return new RecipeRestService(modelMapper, client);

    }
}
