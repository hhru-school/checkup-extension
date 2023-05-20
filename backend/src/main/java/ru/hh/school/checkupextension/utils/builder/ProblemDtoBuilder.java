package ru.hh.school.checkupextension.utils.builder;

import java.util.List;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableVerificationDto;

public class ProblemDtoBuilder {
  public static EditableProblemDto buildProblemDto(
      long id,
      String type,
      String title,
      String description,
      String content,
      byte maxAttempts,
      boolean active,
      String htmlPart,
      String cssPart,
      String jsPart,
      String htmlTemplate,
      String cssTemplate,
      String jsTemplate,
      List<EditableVerificationDto> verificationDto
  ) {

    // TODO: after completion #33
    return null;
//    return new EditableProblemDto(
//        id,
//        type,
//        title,
//        description,
//        content,
//        maxAttempts,
//        active,
//        htmlPart,
//        cssPart,
//        jsPart,
//        htmlTemplate,
//        cssTemplate,
//        jsTemplate,
//        verificationDto
//    );
  }
}
