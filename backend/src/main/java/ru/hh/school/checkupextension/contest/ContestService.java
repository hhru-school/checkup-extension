package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.CheckupInteraction;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.utils.exception.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.SubmissionNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.SubmissionMapper;

public class ContestService {

    private final Repository<ProblemEntity> problemRepository;
    private final Repository<SubmissionEntity> submissionRepository;

    private final CheckupInteraction checkupInteraction;

    @Inject
    public ContestService(CheckupInteraction checkupInteraction,
                          Repository<ProblemEntity> problemRepository,
                          Repository<SubmissionEntity> submissionRepository) {
        this.checkupInteraction = checkupInteraction;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
    }

    @Transactional
    public ContestProblem getProblem(Long problemId) {
        if (checkupInteraction.verifyUserToken(""))
            throw new NotAuthorizedException("");

        var problem = problemRepository.getById(problemId).orElseThrow(() -> new ProblemNotFoundException(problemId));
        return ProblemMapper.toContestProblem(problem);
    }

    @Transactional
    public ContestSubmission createSubmission(String userToken, ContestSubmission submission) {
        verifyUser(userToken);

        var userId = checkupInteraction.getUserId(userToken);
        var entity = SubmissionMapper.toNewEntity(userId, submission);
        var addedEntity = submissionRepository.create(entity);

        return SubmissionMapper.toContestDto(addedEntity);
    }

    @Transactional
    public ContestSubmission getSubmission(String userToken, long submissionId) {
        verifyUser(userToken);

        var submission = getSubmissionById(submissionId);
        return SubmissionMapper.toContestDto(submission);
    }

    @Transactional
    public ContestSubmissionResult getSubmissionStatus(String userToken, long submissionId) {
        verifyUser(userToken);

        var submission = getSubmissionById(submissionId);
        return SubmissionMapper.toContestStatusDto(submission);
    }

    private SubmissionEntity getSubmissionById(long submissionId) {
         return submissionRepository
                .getById(submissionId)
                .orElseThrow(() -> new SubmissionNotFoundException(submissionId));
    }

    private void verifyUser(String userToken) {
        if (checkupInteraction.verifyUserToken(userToken))
            throw new NotAuthorizedException("");
    }
}
