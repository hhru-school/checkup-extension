package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.SubmissionMapper;

public class ContestService {

    private final Repository<ProblemEntity> problemRepository;
    private final Repository<SubmissionEntity> submissionRepository;

    private final CheckupInteraction checkupIntegrator;

    @Inject
    public ContestService(CheckupInteraction checkupIntegrator,
                          Repository<ProblemEntity> problemRepository,
                          Repository<SubmissionEntity> submissionRepository) {
        this.checkupIntegrator = checkupIntegrator;
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
        var problem = problemRepository
                .getById(submission.problemId)
                .orElseThrow(() -> new ProblemNotFoundException(submission.problemId));

        var entity = SubmissionMapper.toNewEntity(userInfo.userId(), submission);
        var addedEntity = submissionRepository.create(entity);
        var dto = SubmissionMapper.toContestDto(addedEntity);

        return dto;
    }

    @Transactional
    public ContestSubmission getSubmission(String userToken, long submissionId) {
        var userInfo = checkupIntegrator.getUserInfo(userToken);
        var submission = getSubmissionById(submissionId);
        return SubmissionMapper.toContestDto(submission);
    }

    @Transactional
    public ContestSubmissionResult getSubmissionStatus(String userToken, long submissionId) {
        var submission = getSubmissionById(submissionId);
        return SubmissionMapper.toContestStatusDto(submission);
    }

    private SubmissionEntity getSubmissionById(long submissionId) {
         return submissionRepository
                .getById(submissionId)
                .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    }
}
