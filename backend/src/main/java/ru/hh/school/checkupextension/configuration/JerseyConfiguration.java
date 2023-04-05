package ru.hh.school.checkupextension.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        // register(API.class);
    }
}
