package ru.hh.school.checkupextension.admin;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemInfoDto;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;
import ru.hh.school.checkupextension.utils.mapper.admin.EditableProblemMapper;

public class AdminService {
  private final static Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

  private final CheckupInteraction checkupInteraction;
  private final ProblemRepository problemRepository;

  public AdminService(CheckupInteraction checkupInteraction, ProblemRepository problemRepository) {
    this.checkupInteraction = checkupInteraction;
    this.problemRepository = problemRepository;
  }

  @Transactional
  public List<EditableProblemInfoDto> getAllProblemsToEdit(
      String userToken,
      int pageNumber,
      int pageSize
  ) {
    checkPermission(userToken);
    return problemRepository.getProblemsFrom(pageNumber, pageSize)
        .stream()
        .map(EditableProblemMapper::toEditableInfoProblemDto)
        .toList();
  }

  @Transactional
  public EditableProblemDto createNewProblem(String userToken, EditableProblemDto problemDto) {
    checkPermission(userToken);
    var problem = EditableProblemMapper.toNewEntity(problemDto);
    var addedProblem = problemRepository.create(problem);
    return EditableProblemMapper.toEditableProblemDto(addedProblem);
  }

  @Transactional
  public EditableProblemDto getProblemToEdit(String userToken, Long problemId) {
    checkPermission(userToken);
    var problem = problemRepository.getById(problemId)
        .orElseThrow(() -> new ProblemNotFoundException(problemId));
    return EditableProblemMapper.toEditableProblemDto(problem);
  }

  public void checkPermission(String userToken) {
    var user = checkupInteraction.getUserInfo(userToken);

    if (!user.isAdmin()) {
      throw new AccessDeniedException();
    }
  }
}
