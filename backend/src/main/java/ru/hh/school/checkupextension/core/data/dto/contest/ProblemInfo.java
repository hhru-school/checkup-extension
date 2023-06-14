package ru.hh.school.checkupextension.core.data.dto.contest;

import ru.hh.school.checkupextension.core.data.entity.Problem;

public class ProblemInfo {
  public final Problem problem;
  public final long totalSubmissions;

  public ProblemInfo(Problem problem, long totalSubmissions) {
    this.problem = problem;
    this.totalSubmissions = totalSubmissions;
  }
}
