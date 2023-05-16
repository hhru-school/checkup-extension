package ru.hh.school.checkupextension.admin;

import ru.hh.school.checkupextension.core.data.dto.admin.EditableTaskDto;
import ru.hh.school.checkupextension.core.data.dto.admin.ShortEditableTaskInfoDto;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;
import ru.hh.school.checkupextension.utils.mapper.admin.EditableTaskMapper;

public class AdminService {
  private final CheckupInteraction checkupInteraction;
  private final ProblemRepository problemRepository;

  public AdminService(CheckupInteraction checkupInteraction, ProblemRepository problemRepository) {
    this.checkupInteraction = checkupInteraction;
    this.problemRepository = problemRepository;
  }

  public ShortEditableTaskInfoDto createNewTask(String userToken, EditableTaskDto taskDto) {
    checkPermission(userToken);

    var task = EditableTaskMapper.toEntity(taskDto);
    var addedTask = problemRepository.create(task);
    return EditableTaskMapper.toShortEditableTaskInfoDto(addedTask);
  }

  public void checkPermission(String userToken) {
    var user = checkupInteraction.getUserInfo(userToken);

    if (!user.isAdmin())
      throw new AccessDeniedException();
  }
}
