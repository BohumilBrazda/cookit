package cz.brazda.cookit.rest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by virtual on 2.5.2017.
 */
@Configuration
public class ApiConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
