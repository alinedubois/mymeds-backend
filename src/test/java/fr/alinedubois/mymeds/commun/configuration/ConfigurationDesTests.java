package fr.alinedubois.mymeds.commun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ConfigurationDesTests {

    @Bean
    public WebTestClient webTestClient(WebApplicationContext applicationContext) {
        return MockMvcWebTestClient
                .bindToApplicationContext(applicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }
}
