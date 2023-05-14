package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public class ProblemMapper {
  public static ContestProblemDto toContestProblem(Problem problem) {
    var res = new ContestProblemDto(
            problem.getId(),
            problem.getTitle(),
            problem.getDescription(),
            problem.getContent(),
            problem.getType(),
            problem.getMaxAttempts(),
            problem.getActive(),
            problem.getTemplate()
    );
    return res;
  }

}
