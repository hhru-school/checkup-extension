package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.checkupextension.admin.AdminResource;
import ru.hh.school.checkupextension.admin.AdminService;
import ru.hh.school.checkupextension.contest.ContestResource;
import ru.hh.school.checkupextension.contest.ContestService;
import ru.hh.school.checkupextension.core.checker.Checker;
import ru.hh.school.checkupextension.core.integration.ContestManager;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.core.repository.VerificationRepository;
import ru.hh.school.checkupextension.utils.exception.mapper.checker.ProblemInitializingExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.ProblemNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.SubmissionNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.core.VerificationNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.integration.AccessDeniedExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.integration.AuthorizedExceptionMapper;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;

@Configuration
@Import({
    // Repository
    ProblemRepository.class,
    SubmissionRepository.class,
    VerificationRepository.class,

    // Services
    ContestService.class,
    AdminService.class,

    // Checkup
    CheckupApiStub.class,

    // Other
    Checker.class,
    ContestManager.class,
    CorsFilter.class,
})
public class AppConfiguration {
  @Bean
  public ResourceConfig jerseyConfig() {
    var config = new ResourceConfig();

    config.register(ContestResource.class);
    config.register(AdminResource.class);

    // Exceptions mappers
    // Integration
    config.register(AuthorizedExceptionMapper.class);
    config.register(AccessDeniedExceptionMapper.class);
    // Core
    config.register(VerificationNotFoundExceptionMapper.class);
    config.register(ProblemNotFoundExceptionMapper.class);
    config.register(SubmissionNotFoundExceptionMapper.class);
    // Checker
    config.register(ProblemInitializingExceptionMapper.class);

    // Фильтр CORS
    config.register(CorsFilter.class);

    return config;
  }
}
