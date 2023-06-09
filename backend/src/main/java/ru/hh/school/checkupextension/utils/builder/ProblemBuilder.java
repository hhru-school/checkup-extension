package ru.hh.school.checkupextension.utils.builder;

import java.util.List;
import ru.hh.school.checkupextension.core.data.entity.JsonContainer;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Verification;

public class ProblemBuilder {
  public static Problem buildNewProblem(
      String title,
      String description,
      String content,
      boolean active,
      byte maxAttempts,
      byte type,
      JsonContainer template,
      JsonContainer solution,
      List<Verification> verifications
  ) {
    return buildProblem(
        null, title, description, content,
        active, maxAttempts, type,
        template, solution,
        verifications
    );
  }

  public static Problem buildProblem(
      Long id,
      String title,
      String description,
      String content,
      boolean active,
      byte maxAttempts,
      byte type,
      JsonContainer template,
      JsonContainer solution,
      List<Verification> verifications
  ) {
    var problem = new Problem();
    problem.setId(id);
    problem.setTitle(title);
    problem.setDescription(description);
    problem.setContent(content);
    problem.setActive(active);
    problem.setMaxAttempts(maxAttempts);
    problem.setType(type);
    problem.setReferenceSolution(solution);
    problem.setTemplate(template);
    problem.setVerifications(verifications);
    return problem;
  }
}
