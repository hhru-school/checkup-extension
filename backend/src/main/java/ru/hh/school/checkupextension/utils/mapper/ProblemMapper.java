package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;

public class ProblemMapper {
  public static ContestProblemDto toContestProblem(Problem problem) {
    var template = problem.getTemplate();
    String type = ProblemType.getTitleBy(problem.getType()); // Получаем строковое представление типа задачи
    return new ContestProblemDto(
            problem.getId(),
            problem.getTitle(),
            problem.getDescription(),
            problem.getContent(),
            type, // Используем строковое представление типа задачи
            problem.getMaxAttempts(),
            problem.getActive(),
            template.getHtmlTemplate(),
            template.getCssTemplate(),
            template.getJsTemplate()
    );
  }

  private static Problem.Template extractTemplate(ContestProblemDto problem) {
    var template = new Problem.Template();
    template.setHtmlTemplate(problem.htmlPart);
    template.setCssTemplate(problem.cssPart);
    template.setJsTemplate(problem.jsPart);
    return template;
  }

}
