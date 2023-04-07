package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.hh.school.checkupextension.test.TestResource;

@Configuration
public class JerseyConfig {
    @Bean
    public ResourceConfig config() {
        ResourceConfig config = new ResourceConfig();
        config.register(TestResource.class);
        return config;
    }
}
