package cz.brazda.cookit.client.vaadin;

import cz.brazda.cookit.config.PersistenceConfig;
import cz.brazda.cookit.repository.service.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceConfig.class})
@ComponentScan("cz.brazda.cookit.client")
public class VaadinAppConfig {
}
