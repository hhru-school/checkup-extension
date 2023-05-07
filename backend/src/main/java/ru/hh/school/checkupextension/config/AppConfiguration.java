package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.checkupextension.contest.ContestApiResource;
import ru.hh.school.checkupextension.contest.ContestService;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.core.repository.VerificationRepository;
import ru.hh.school.checkupextension.utils.exception.mapper.integration.AuthorizedExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.ProblemNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.SubmissionNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.VerificationNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;
import ru.hh.school.checkupextension.utils.stub.ProblemRepositoryStub;

@Configuration
@Import({
        // Repository
        VerificationRepository.class,
//        ProblemRepository.class,
        SubmissionRepository.class,

        // Services
        ContestService.class,
//        AdminService.class,

        // Checkup
        CheckupApiStub.class,

        // TODO: Debug
        ProblemRepositoryStub.class
})
public class AppConfiguration {
    @Bean
    public ResourceConfig jerseyConfig() {
        var config = new ResourceConfig();

        config.register(ContestApiResource.class);

        config.register(VerificationNotFoundExceptionMapper.class);
        config.register(ProblemNotFoundExceptionMapper.class);
        config.register(SubmissionNotFoundExceptionMapper.class);
        config.register(AuthorizedExceptionMapper.class);

        return config;
    }
}
