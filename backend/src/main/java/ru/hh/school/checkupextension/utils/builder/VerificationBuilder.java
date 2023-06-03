package ru.hh.school.checkupextension.utils.builder;

import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Verification;

public class VerificationBuilder {
  public static Verification buildNewVerification(String content) {
    return buildVerification(null, null, content);
  }

  public static Verification buildVerification(Long id, Long problemId, String content) {
    var verification = new Verification();
    verification.setId(id);
    verification.setProblem(new Problem());
    verification.getProblem().setId(problemId);
    verification.setContent(content);
    return verification;
  }
}
