package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.transaction.annotation.Transactional;

import ru.hh.school.checkupextension.core.checker.ContestManager;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfoDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.SubmissionMapper;

import java.util.List;

/**
 * Класс, который представляет собой сервисную службу, содержащую бизнес-логику для обработки запросов,
 * связанных с контестом.
 */
public class ContestService {
  private static final Logger LOGGER = getLogger(ContestService.class);

  private final ProblemRepository problemRepository;
  private final SubmissionRepository submissionRepository;

  private final ContestManager contestManager;
  private final CheckupInteraction checkupIntegrator;

  @Inject
  public ContestService(
      CheckupInteraction checkupIntegrator,
      ContestManager contestManager,
      ProblemRepository problemRepository,
      SubmissionRepository submissionRepository
  ) {
    this.checkupIntegrator = checkupIntegrator;
    this.contestManager = contestManager;
    this.problemRepository = problemRepository;
    this.submissionRepository = submissionRepository;
  }

  @Transactional
  public ContestProblemDto getProblem(Long problemId) {
    var problem = problemRepository.getById(problemId).orElseThrow(() -> new ProblemNotFoundException(problemId));
    return ProblemMapper.toContestProblem(problem);
  }

  @Transactional
  public ContestProblemDto createProblem(Problem problem) {
    return ProblemMapper.toContestProblem(problemRepository.create(problem));
  }

  @Transactional
  public ContestProblemDto updateProblem(long id, Problem problem) {
    problem.setId(id);
    return ProblemMapper.toContestProblem(problemRepository.update(problem));
  }

  @Transactional
  public ContestSubmissionDto createSubmission(String userToken, ContestSubmissionDto submission) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    var userId = userInfo.userId();

    var problemId = submission.problemId;
    var problem = problemRepository
        .getById(problemId)
        .orElseThrow(() -> new ProblemNotFoundException(problemId));

    var totalSubmissions = submissionRepository.getTotalSubmissionsOfTaskForUser(userId, problemId);

    LOGGER.info("Total submissions {} [max: {}] from user {}", totalSubmissions, problem.getMaxAttempts(), userId);
    contestManager.allowSolvingProblem(userId, totalSubmissions, problem);

    var entity = SubmissionMapper.toNewEntity(userId, submission);
    var addedEntity = submissionRepository.create(entity);

    return SubmissionMapper.toContestDto(addedEntity);
  }

  @Transactional
  public ContestSubmissionDto getSubmission(String userToken, long submissionId) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    LOGGER.info("Request the submission id {} from user {}", submissionId, userInfo.userId());
    var submission = submissionRepository
        .getForUserById(userInfo.userId(), submissionId)
        .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    return SubmissionMapper.toContestDto(submission);
  }

  @Transactional
  public List<ContestSubmissionShortInfoDto> getUserSubmissionsInfo(String userToken, long problemId) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    return submissionRepository.getUserSubmissionsShortInfo(userInfo.userId(), problemId)
        .stream()
        .map(SubmissionMapper::toContestSubmissionShortInfo)
        .toList();
  }

  @Transactional
  public ContestSubmissionResultDto getSubmissionStatus(long submissionId) {
    var submission = submissionRepository
        .getById(submissionId)
        .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    return SubmissionMapper.toContestStatusDto(submission);
  }
}
