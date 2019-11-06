package cz.brazda.cookit.client.vaadin;

import cz.brazda.cookit.config.PersistenceConfig;
import cz.brazda.cookit.repository.service.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@Import({PersistenceConfig.class})
@EnableTransactionManagement
@ComponentScan("cz.brazda.cookit.client")
public class VaadinAppConfig{

}
