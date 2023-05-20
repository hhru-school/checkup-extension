package ru.hh.school.checkupextension.utils.builder;

import ru.hh.school.checkupextension.core.data.dto.admin.EditableVerificationDto;

public class VerificationDtoBuilder {
  public static EditableVerificationDto buildEditableVerificationDto(long id, String content) {
    return new EditableVerificationDto(id, content);
  }
}
