package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.checker.Checker;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfoDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ProblemInfo;
import ru.hh.school.checkupextension.core.data.dto.contest.UserSubmissionsDto;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.integration.ContestManager;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.contest.ContestProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.contest.ContestSubmissionMapper;
import ru.hh.school.checkupextension.utils.mapper.contest.UserSolutionMapper;

public class ContestService {
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

  @Transactional
  public ContestSubmissionShortInfoDto handleSubmission(String userToken, ContestSubmissionDto submission) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
    var userId = userInfo.userId();

    var problemId = submission.problemId;
    var problem = problemRepository
        .getById(problemId)
        .orElseThrow(() -> new ProblemNotFoundException(problemId));

    var totalSubmissions = submissionRepository.getTotalSubmissionsOfTaskForUser(userId, problemId);

    var problemInfo = new ProblemInfo(problem, totalSubmissions);
    checkPermissionForUser(userId, problemInfo);

    var entity = ContestSubmissionMapper.toNewEntity(userId, submission);
    var addedEntity = submissionRepository.create(entity);
    var checkedDto = ContestSubmissionMapper.toContestDto(addedEntity);

    var res = checkSolution(checkedDto, problemInfo);
    var status = res.success() ? SubmissionsStatus.ACCEPTED : SubmissionsStatus.REFUSED;
    submissionRepository.updateSubmissionStatus(checkedDto.id, status.getCode());

    return new ContestSubmissionShortInfoDto(checkedDto.id, checkedDto.creationDateTime, status.getTitle());
  }

  private void checkPermissionForUser(long userId, ProblemInfo problemInfo) {
    var problem = problemInfo.problem;
    var totalSubmissions = problemInfo.totalSubmissions;

    contestManager.allowSolvingProblem(userId, problemInfo);
  }

  private TestInfo checkSolution(ContestSubmissionDto submissionDto, ProblemInfo problemInfo) {
    var userSolution = UserSolutionMapper.toUserSolutionDto(submissionDto, problemInfo.problem);
    return checker.check(userSolution);
  }

  @Transactional
  public ContestSubmissionDto getSubmission(String userToken, long submissionId) {
    var userInfo = checkupIntegrator.getUserInfo(userToken);
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
