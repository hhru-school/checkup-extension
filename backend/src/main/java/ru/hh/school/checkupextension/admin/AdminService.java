package ru.hh.school.checkupextension.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.checkupextension.core.checker.register.ScreenshotsCreator;
import ru.hh.school.checkupextension.core.data.dto.PaginationResultDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemInfoDto;
import ru.hh.school.checkupextension.core.data.dto.admin.ShortEditableProblemDto;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;
import ru.hh.school.checkupextension.utils.mapper.PaginationResultMapper;
import ru.hh.school.checkupextension.utils.mapper.admin.EditableProblemMapper;

public class AdminService {
  private final CheckupInteraction checkupInteraction;
  private final ProblemRepository problemRepository;

  public AdminService(CheckupInteraction checkupInteraction, ProblemRepository problemRepository) {
    this.checkupInteraction = checkupInteraction;
    this.problemRepository = problemRepository;
  }

  @Transactional
  public PaginationResultDto<EditableProblemInfoDto> getAllProblemsToEdit(
      String userToken,
      int pageNumber,
      int pageSize
  ) {
    checkPermission(userToken);
    return PaginationResultMapper.toPaginationResultDto(
        problemRepository.getProblems(pageNumber, pageSize),
        EditableProblemMapper::toEditableInfoProblemDto
    );
  }

  @Transactional
  public EditableProblemDto getProblemToEdit(String userToken, Long problemId) {
    checkPermission(userToken);
    var problem = problemRepository.getById(problemId)
        .orElseThrow(() -> new ProblemNotFoundException(problemId));
    return EditableProblemMapper.toEditableProblemDto(problem);
  }

  @Transactional
  public ShortEditableProblemDto createNewProblem(String userToken, EditableProblemDto problemDto) {
    checkPermission(userToken);
    var problem = EditableProblemMapper.toNewEntity(problemDto);
    var addedProblem = problemRepository.create(problem);
    ScreenshotsCreator.createScreenshot(addedProblem);
    return EditableProblemMapper.toShortEditableProblemDto(addedProblem);
  }

  @Transactional
  public void updateExistProblem(String userToken, EditableProblemDto problemDto) {
    checkPermission(userToken);
    var problem = EditableProblemMapper.toExistEntity(problemDto);
    problemRepository.update(problem);
    ScreenshotsCreator.updateScreenshot(problem);
  }

  public void checkPermission(String userToken) {
    var user = checkupInteraction.getUserInfo(userToken);

    if (!user.isAdmin()) {
      throw new AccessDeniedException();
    }
  }
}
