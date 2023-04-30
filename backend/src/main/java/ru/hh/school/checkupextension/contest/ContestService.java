package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.CheckupInteraction;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.utils.exception.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;

import java.util.List;

/**
 * Класс, который представляет собой сервисную службу, содержащую бизнес-логику для обработки запросов,
 * связанных с контестом.
 */
public class ContestService {

    private final static Logger LOGGER = getLogger(ContestService.class);
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
    public List<ProblemEntity> getAllProblems() {
        return problemRepository.getAll();
    }

    @Transactional
    public ProblemEntity createProblem(ProblemEntity problem) {
        return problemRepository.create(problem);
    }

    @Transactional
    public ProblemEntity updateProblem(long id, ProblemEntity problem) {
        problem.setId(id);
        return problemRepository.update(problem);
    }

    @Transactional
    public void deleteProblem(long id) {
        problemRepository.delete(id);
    }
}
