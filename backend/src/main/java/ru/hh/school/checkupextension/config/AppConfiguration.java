package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.checkupextension.contest.ContestApiResource;
import ru.hh.school.checkupextension.contest.ContestService;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.core.repository.VerificationRepository;
import ru.hh.school.checkupextension.utils.exception.mapper.AuthorizedExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.ProblemNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.SubmissionNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.VerificationNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;

@Configuration
@Import({
    VerificationRepository.class,
    ProblemRepository.class,
    SubmissionRepository.class,
    ContestService.class,
    CheckupApiStub.class,
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
