package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.transaction.annotation.Transactional;

import ru.hh.school.checkupextension.core.checker.ContestManager;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfo;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.core.repository.SubmissionRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.SubmissionMapper;

import java.util.List;

public class ContestService {
    private static final Logger LOGGER = getLogger(ContestService.class);

    private final Repository<ProblemEntity> problemRepository;
    private final SubmissionRepository submissionRepository;

    private final ContestManager contestManager;
    private final CheckupInteraction checkupIntegrator;

    @Inject
    public ContestService(CheckupInteraction checkupIntegrator,
                          ContestManager contestManager,
                          Repository<ProblemEntity> problemRepository,
                          SubmissionRepository submissionRepository) {
        this.checkupIntegrator = checkupIntegrator;
        this.contestManager = contestManager;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
    }

    @Transactional
    public ContestProblem getProblem(Long problemId) {
        var problem = problemRepository.getById(problemId).orElseThrow(() -> new ProblemNotFoundException(problemId));
        return ProblemMapper.toContestProblem(problem);
    }

    @Transactional
    public ContestSubmission createSubmission(String userToken, ContestSubmission submission) {
        var userInfo = checkupIntegrator.getUserInfo(userToken);
        var userId = userInfo.userId();

        var problemId= submission.problemId;
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
    public ContestSubmission getSubmission(String userToken, long submissionId) {
        var userInfo = checkupIntegrator.getUserInfo(userToken);
        LOGGER.info("Request the submission id {} from user {}", submissionId, userInfo.userId());
        var submission = submissionRepository
                .getForUserById(userInfo.userId(), submissionId)
                .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
        return SubmissionMapper.toContestDto(submission);
    }

    @Transactional
    public List<ContestSubmissionShortInfo> getUserSubmissionsInfo(String userToken, long problemId) {
        var userInfo = checkupIntegrator.getUserInfo(userToken);
        return submissionRepository.getUserSubmissionsShortInfo(userInfo.userId(), problemId)
                .stream()
                .map(SubmissionMapper::toContestSubmissionShortInfo)
                .toList();
    }

    @Transactional
    public ContestSubmissionResult getSubmissionStatus(long submissionId) {
        var submission = submissionRepository
                .getById(submissionId)
                .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
        return SubmissionMapper.toContestStatusDto(submission);
    }
}
