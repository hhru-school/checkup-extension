package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.checkupextension.utils.exception.mapper.AuthorizedExceptionMapper;
import ru.hh.school.checkupextension.contest.ContestApiResource;
import ru.hh.school.checkupextension.contest.ContestService;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;

@Configuration
@Import({
        // TODO: Add dependencies
        ContestService.class,
        CheckupApiStub.class,
})
public class AppConfiguration {
    @Bean
    public ResourceConfig jerseyConfig() {
        var config = new ResourceConfig();

        // TODO: Add resources
        config.register(ContestApiResource.class);

        // TODO: Add ExceptionMappers
        config.register(AuthorizedExceptionMapper.class);

        return config;
    }
}
