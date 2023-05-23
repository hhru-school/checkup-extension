package ru.hh.school.checkupextension.utils.mapper.admin;

import java.util.List;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableVerificationDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.JsonContainer;
import ru.hh.school.checkupextension.core.data.entity.Verification;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;
import ru.hh.school.checkupextension.utils.builder.ProblemBuilder;
import ru.hh.school.checkupextension.utils.builder.VerificationBuilder;

public class EditableProblemMapper {
  public static Problem toEntity(EditableProblemDto problemDto) {
    var solution = extractSolution(problemDto);
    var template = extractTemplate(problemDto);
    var verifications = extractVerifications(problemDto);
    var type = ProblemType.getCodeBy(problemDto.type);

    return ProblemBuilder.buildProblem(
        problemDto.id,
        problemDto.title,
        problemDto.description,
        problemDto.content,
        problemDto.active,
        problemDto.maxAttempts,
        type,
        template,
        solution,
        verifications);
  }

  private static JsonContainer extractSolution(EditableProblemDto problemDto) {
    return JsonContainer.fill(
      problemDto.htmlPartSolution,
      problemDto.cssPartSolution,
      problemDto.jsPartSolution);
  }

  public static EditableProblemDto toEditableProblemDto(Problem problem) {
    var type = ProblemType.getTitleBy(problem.getType());
    var solution = problem.getReferenceSolution();
    var template = problem.getTemplate();
    var verificationDto = formatVerificationDto(problem);

    return new EditableProblemDto(
        problem.getId(),
        type,
        problem.getTitle(),
        problem.getDescription(),
        problem.getContent(),
        problem.getMaxAttempts(),
        problem.getActive(),
        solution.getHtmlPart(),
        solution.getCssPart(),
        solution.getJsPart(),
        template.getHtmlPart(),
        template.getCssPart(),
        template.getJsPart(),
        verificationDto
    );
  }

  private static List<EditableVerificationDto> formatVerificationDto(Problem problem) {
    var verifications = problem.getVerifications();
    return verifications.stream()
        .map(v -> new EditableVerificationDto(v.getId(), v.getContent()))
        .toList();
  }

  private static JsonContainer extractTemplate(EditableProblemDto problemDto) {
    return JsonContainer.fill(
        problemDto.htmlTemplate,
        problemDto.cssTemplate,
        problemDto.jsTemplate);
  }

  private static List<Verification> extractVerifications(EditableProblemDto problemDto) {
    return problemDto.test.stream()
        .map(dto -> extractVerification(problemDto.id, dto))
        .toList();
  }

  private static Verification extractVerification(long problemId, EditableVerificationDto verificationDto) {
    return VerificationBuilder.buildVerification(
        verificationDto.id(),
        problemId,
        verificationDto.content());
  }
}
