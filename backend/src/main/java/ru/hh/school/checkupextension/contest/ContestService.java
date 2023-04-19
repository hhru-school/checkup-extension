package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.CheckupInteraction;
import ru.hh.school.checkupextension.core.data.Problem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.data.nullobject.EmptyProblem;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;

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
    public ContestProblem getProblem() {
        if (checkupInteraction.verifyUserToken(""))
            throw new NotAuthorizedException("");

        return ProblemMapper.toContestProblem(
                problemRepository.getById(1L).get());
    }
}
