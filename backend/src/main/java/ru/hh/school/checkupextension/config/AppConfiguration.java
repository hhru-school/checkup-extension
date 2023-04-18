package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.checkupextension.core.data.controller.ProblemResource;
import ru.hh.school.checkupextension.core.data.controller.SubmissionResource;
import ru.hh.school.checkupextension.core.data.controller.VerificationResource;
import ru.hh.school.checkupextension.core.data.daoimpl.ProblemDaoImpl;
import ru.hh.school.checkupextension.core.data.daoimpl.SubmissionDaoImpl;
import ru.hh.school.checkupextension.core.data.daoimpl.VerificationDaoImpl;
import ru.hh.school.checkupextension.core.data.service.ProblemService;
import ru.hh.school.checkupextension.core.data.service.SubmissionService;
import ru.hh.school.checkupextension.core.data.service.VerificationService;
import ru.hh.school.checkupextension.utils.exception.mapper.AuthorizedExceptionMapper;
import ru.hh.school.checkupextension.contest.ContestApiResource;
import ru.hh.school.checkupextension.contest.ContestService;
import ru.hh.school.checkupextension.utils.exception.mapper.ProblemNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.SubmissionNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.mapper.VerificationNotFoundExceptionMapper;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;

@Configuration
@Import({
    VerificationDaoImpl.class,
    VerificationService.class,
    ProblemDaoImpl.class,
    ProblemService.class,
    SubmissionDaoImpl.class,
    SubmissionService.class,
    ContestService.class,
    CheckupApiStub.class,
})
public class AppConfiguration {
  @Bean
  public ResourceConfig jerseyConfig() {
    var config = new ResourceConfig();

    config.register(VerificationResource.class);
    config.register(ProblemResource.class);
    config.register(SubmissionResource.class);
    config.register(ContestApiResource.class);

    config.register(VerificationNotFoundExceptionMapper.class);
    config.register(ProblemNotFoundExceptionMapper.class);
    config.register(SubmissionNotFoundExceptionMapper.class);
    config.register(AuthorizedExceptionMapper.class);

    return config;
  }
}
