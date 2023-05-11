package ru.hh.school.checkupextension.core.checker;

import jakarta.inject.Inject;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

public class ContestManager {
  private final CheckupInteraction checkupIntegrator;

  @Inject
  public ContestManager(CheckupInteraction checkupIntegrator) {
    this.checkupIntegrator = checkupIntegrator;
  }

  public void allowSolvingProblem(long userId, long totalSubmissions, Problem problem) {
    if (!checkupIntegrator.userHasTimeToSolveProblems(userId)
        || totalSubmissions > problem.getMaxAttempts()) {
      throw new AccessDeniedException();
    }
  }
}
