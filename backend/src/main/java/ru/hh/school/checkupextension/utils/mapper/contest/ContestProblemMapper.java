package ru.hh.school.checkupextension.utils.mapper.contest;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemInfoDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;

public class ContestProblemMapper {
  public static ContestProblemInfoDto toContestProblemInfo(Problem problem) {
    String type = ProblemType.getTitleBy(problem.getType());
    return new ContestProblemInfoDto(
        problem.getId(),
        problem.getTitle(),
        problem.getDescription(),
        type);
  }

  public static ContestProblemDto toContestProblem(Problem problem) {
    var template = problem.getTemplate();
    String type = ProblemType.getTitleBy(problem.getType());

    return new ContestProblemDto(
            problem.getId(),
            problem.getTitle(),
            problem.getDescription(),
            problem.getContent(),
            type, // Используем строковое представление типа задачи
            problem.getMaxAttempts(),
            problem.getActive(),
            template.getHtmlPart(),
            template.getCssPart(),
            template.getJsPart()
    );
  }
}
