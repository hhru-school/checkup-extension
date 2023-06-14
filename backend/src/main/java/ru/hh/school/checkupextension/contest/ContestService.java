package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.checker.Checker;
import ru.hh.school.checkupextension.core.checker.ContestManager;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ProblemInfo;
import ru.hh.school.checkupextension.core.data.dto.contest.UserSubmissionsDto;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.contest.ContestProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.contest.ContestSubmissionMapper;
import ru.hh.school.checkupextension.utils.mapper.contest.UserSolutionMapper;

/**
 * Класс, который представляет собой сервисную службу, содержащую бизнес-логику для обработки запросов,
 * связанных с контестом.
 */
public class ContestService {
  private static final Logger LOGGER = getLogger(ContestService.class);

  private final ProblemRepository problemRepository;
  private final SubmissionRepository submissionRepository;

  private final Checker checker;

  private final ContestManager contestManager;
  private final CheckupInteraction checkupIntegrator;

  @Inject
  public ContestService(
      CheckupInteraction checkupIntegrator,
      Checker checker,
      ContestManager contestManager,
      ProblemRepository problemRepository,
      SubmissionRepository submissionRepository
  ) {
    this.checkupIntegrator = checkupIntegrator;
    this.checker = checker;
    this.contestManager = contestManager;
    this.problemRepository = problemRepository;
    this.submissionRepository = submissionRepository;
  }

  @Transactional
  public ContestProblemDto getProblem(Long problemId) {
    var problem = problemRepository.getById(problemId).orElseThrow(() -> new ProblemNotFoundException(problemId));
    return ContestProblemMapper.toContestProblem(problem);
  }

  @Transactional
  public ContestDto getContestProblemsInfo() {
    var problems = problemRepository.getActiveProblems()
        .stream()
        .map(ContestProblemMapper::toContestProblemInfo)
        .toList();
    return new ContestDto(problems);
  }

  public ContestSubmissionDto handleSubmission(String userToken, ContestSubmissionDto submission) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    var userId = userInfo.userId();

    var problemInfo = getProblemInfoForUser(userId, submission);
    checkPermissionForUser(userId, problemInfo);
    var registeredSubmission = registerSubmissionForUser(userId, submission);
    Executors.newSingleThreadExecutor().execute(() ->
        checkSolution(submission, problemInfo)
    );

    return registeredSubmission;
  }

  @Transactional
  private ProblemInfo getProblemInfoForUser(long userId, ContestSubmissionDto submissionDto) {
    var problemId = submissionDto.problemId;

    var problem = problemRepository
        .getById(problemId)
        .orElseThrow(() -> new ProblemNotFoundException(problemId));

    var totalSubmissions = submissionRepository.getTotalSubmissionsOfTaskForUser(userId, problemId);

    return new ProblemInfo(problem, totalSubmissions);
  }

  private void checkPermissionForUser(long userId, ProblemInfo problemInfo) {
    var problem = problemInfo.problem;
    var totalSubmissions = problemInfo.totalSubmissions;

    LOGGER.info("Total submissions {} [max: {}] from user {}", totalSubmissions, problem.getMaxAttempts(), userId);
    contestManager.allowSolvingProblem(userId, problemInfo);
  }

  private TestInfo checkSolution(ContestSubmissionDto submissionDto, ProblemInfo problemInfo) {
    var userSolution = UserSolutionMapper.toUserSolutionDto(submissionDto, problemInfo.problem);
    return checker.check(userSolution);
  }

  @Transactional
  public ContestSubmissionDto registerSubmissionForUser(long userId, ContestSubmissionDto submission) {
    var entity = ContestSubmissionMapper.toNewEntity(userId, submission);
    var addedEntity = submissionRepository.create(entity);
    return ContestSubmissionMapper.toContestDto(addedEntity);
  }

  @Transactional
  public ContestSubmissionDto getSubmission(String userToken, long submissionId) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    LOGGER.info("Request the submission id {} from user {}", submissionId, userInfo.userId());
    var submission = submissionRepository
        .getForUserById(userInfo.userId(), submissionId)
        .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    return ContestSubmissionMapper.toContestDto(submission);
  }

  @Transactional
  public UserSubmissionsDto getUserSubmissionsInfo(String userToken, long problemId) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    var submissions = submissionRepository.getUserSubmissionsShortInfo(userInfo.userId(), problemId)
        .stream()
        .map(ContestSubmissionMapper::toContestSubmissionShortInfo)
        .toList();
    return new UserSubmissionsDto(submissions);
  }

  @Transactional
  public ContestSubmissionResultDto getSubmissionStatus(long submissionId) {
    var submission = submissionRepository
        .getById(submissionId)
        .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    return ContestSubmissionMapper.toContestStatusDto(submission);
  }
}
