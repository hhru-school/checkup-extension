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
  public static Problem toNewEntity(EditableProblemDto problemDto) {
    var solution = extractSolution(problemDto);
    var template = extractTemplate(problemDto);
    var verifications = extractCreatedVerifications(problemDto);
    var codeOfType = ProblemType.getCodeBy(problemDto.type);

    return ProblemBuilder.buildNewProblem(
        problemDto.title,
        problemDto.description,
        problemDto.content,
        problemDto.active,
        problemDto.maxAttempts,
        codeOfType,
        template,
        solution,
        verifications
    );
  }

  private static JsonContainer extractSolution(EditableProblemDto problemDto) {
    return JsonContainer.fill(
        problemDto.htmlPartSolution,
        problemDto.cssPartSolution,
        problemDto.jsPartSolution
    );
  }

  public static EditableProblemDto toEditableProblemDto(Problem problem) {
    var nameOfType = ProblemType.getTitleBy(problem.getType());
    var solution = problem.getReferenceSolution();
    var template = problem.getTemplate();
    var verificationDto = formatVerificationDto(problem);

    return new EditableProblemDto(
        problem.getId(),
        nameOfType,
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
        problemDto.jsTemplate
    );
  }

  private static List<Verification> extractCreatedVerifications(EditableProblemDto problemDto) {
    return problemDto.test.stream()
        .map(EditableProblemMapper::extractCreatedVerification)
        .toList();
  }

  private static Verification extractCreatedVerification(EditableVerificationDto verificationDto) {
    return VerificationBuilder.buildNewVerification(
        verificationDto.content()
    );
  }
}
