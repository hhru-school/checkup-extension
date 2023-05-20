package ru.hh.school.checkupextension.utils.mapper.admin;

import java.util.List;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableVerificationDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Verification;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;
import ru.hh.school.checkupextension.utils.builder.ProblemBuilder;
import ru.hh.school.checkupextension.utils.builder.TemplateBuilder;
import ru.hh.school.checkupextension.utils.builder.VerificationBuilder;

public class EditableProblemMapper {
  public static Problem toEntity(EditableProblemDto problemDto) {
    var template = extractTemplate(problemDto);
    var verifications = extractVerifications(problemDto);
    var type = ProblemType.valueOf(problemDto.type).getCode();
    // TODO: after completion #33
    return ProblemBuilder.buildProblem(
        problemDto.Id,
        problemDto.title,
        problemDto.description,
        "", //problemDto.conent,
        true, //problemDto.active,
        (byte)10,//problemDto.maxAttempts,
        type,
        template,
        verifications);
  }

  public static EditableProblemDto toEditableProblemDto(Problem problem) {
    // TODO: Добавить преобразование
    return null;
  }

  private static Problem.Template extractTemplate(EditableProblemDto problemDto) {
    return TemplateBuilder.buildTemplate(
        problemDto.htmlTemplate,
        problemDto.cssTemplate,
        problemDto.jsTemplate);
  }

  private static List<Verification> extractVerifications(EditableProblemDto problemDto) {
    return problemDto.test.stream()
        .map(dto -> extractVerification(problemDto.Id, dto))
        .toList();
  }

  private static Verification extractVerification(long problemId, EditableVerificationDto verificationDto) {
    return VerificationBuilder.buildVerification(
        verificationDto.id(),
        problemId,
        verificationDto.content());
  }
}
