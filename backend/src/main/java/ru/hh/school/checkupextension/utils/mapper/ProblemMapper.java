package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public class ProblemMapper {
  public static ContestProblemDto toContestProblem(Problem problem) {
    var res = new ContestProblemDto(
        problem.getId(),
        problem.getCondition(),
        problem.getType(),
        problem.getType()
    );

    return res;
  }
}
