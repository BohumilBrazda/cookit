package client.fx.config;

import client.repository.config.RepositoryConfig;
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


}
