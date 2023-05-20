package ru.hh.school.checkupextension.utils.builder;

import ru.hh.school.checkupextension.core.data.entity.Verification;

public class VerificationBuilder {
  public static Verification buildVerification(Long id, Long problemId, String content) {
    var verification = new Verification();
    verification.setId(id);
    verification.getProblem().setId(problemId);
    verification.setContent(content);
    return verification;
  }
}