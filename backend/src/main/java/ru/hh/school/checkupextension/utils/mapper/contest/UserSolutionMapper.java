package ru.hh.school.checkupextension.utils.mapper.contest;

import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.enums.ProblemType;

public class UserSolutionMapper {
  public static UserSolution toUserSolutionDto(ContestSubmissionDto submission, Problem problem) {
    var problemType = ProblemType.getValue(problem.getType());
    return new UserSolution(
        submission.id,
        submission.problemId,
        problemType,
        submission.htmlPart,
        submission.cssPart,
        submission.jsPart
    );
  }
}
