package ru.hh.school.checkupextension.core.integration;

import jakarta.inject.Inject;
import ru.hh.school.checkupextension.core.data.dto.contest.ProblemInfo;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

public class ContestManager {
  private final CheckupInteraction checkupIntegrator;

  @Inject
  public ContestManager(CheckupInteraction checkupIntegrator) {
    this.checkupIntegrator = checkupIntegrator;
  }

  public void allowSolvingProblem(long userId, ProblemInfo problemInfo) {
    var problem = problemInfo.problem;
    var totalSubmissions = problemInfo.totalSubmissions;

    if (!checkupIntegrator.userHasTimeToSolveProblems(userId)
        || totalSubmissions > problem.getMaxAttempts()) {
      throw new AccessDeniedException();
    }
  }
}
