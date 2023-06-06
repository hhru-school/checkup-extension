package ru.hh.school.checkupextension.utils.builder;

import java.time.LocalDateTime;
import ru.hh.school.checkupextension.core.data.entity.JsonContainer;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Submission;

public class SubmissionBuilder {
  public static Submission buildSubmission(
      Long userId,
      Long problemId,
      byte status,
      JsonContainer solution
  ) {
    var entity = new Submission();
    entity.setProblem(new Problem());
    entity.setUser(userId);
    entity.getProblem().setId(problemId);
    entity.setStatus(status);
    entity.setCreationDateTime(LocalDateTime.now());
    entity.setUserSolution(solution);
    return entity;
  }
}
