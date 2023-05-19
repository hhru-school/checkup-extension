package ru.hh.school.checkupextension.admin;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;
import ru.hh.school.checkupextension.utils.mapper.ProblemMapper;
import ru.hh.school.checkupextension.utils.mapper.admin.EditableProblemMapper;

import java.util.List;

public class AdminService {
  private final CheckupInteraction checkupInteraction;
  private final ProblemRepository problemRepository;

  public AdminService(CheckupInteraction checkupInteraction, ProblemRepository problemRepository) {
    this.checkupInteraction = checkupInteraction;
    this.problemRepository = problemRepository;
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

  public EditableProblemDto createNewProblem(String userToken, EditableProblemDto problemDto) {
    checkPermission(userToken);

    var problem = EditableProblemMapper.toEntity(problemDto);
    // TODO: Add cascade for the entity
    var addedProblem = problemRepository.create(problem);
    return EditableProblemMapper.toEditableProblemDto(addedProblem);
  }

  public void checkPermission(String userToken) {
    var user = checkupInteraction.getUserInfo(userToken);

    if (!user.isAdmin()) {
      throw new AccessDeniedException();
    }
  }
}
