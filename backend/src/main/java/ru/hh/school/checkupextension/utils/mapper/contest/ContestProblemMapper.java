package ru.hh.school.checkupextension.utils.mapper.contest;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemInfoDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;

public class ContestProblemMapper {
  public static ContestProblemInfoDto toContestProblemInfo(Problem problem) {
    String nameOfType = ProblemType.getTitleBy(problem.getType());
    return new ContestProblemInfoDto(
        problem.getId(),
        problem.getTitle(),
        problem.getDescription(),
        nameOfType
    );
  }

  public static ContestProblemDto toContestProblem(Problem problem) {
    var template = problem.getTemplate();
    String nameOfType = ProblemType.getTitleBy(problem.getType());

    return new ContestProblemDto(
        problem.getId(),
        problem.getTitle(),
        problem.getDescription(),
        problem.getContent(),
        nameOfType,
        problem.getMaxAttempts(),
        problem.getActive(),
        template.getHtmlPart(),
        template.getCssPart(),
        template.getJsPart()
    );
  }
}
