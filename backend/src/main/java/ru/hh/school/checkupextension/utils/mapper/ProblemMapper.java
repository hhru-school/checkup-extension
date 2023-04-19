package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.Problem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;

public class ProblemMapper {
    public static ContestProblem toContestProblem(Problem problem) {
        var res = new ContestProblem(
                problem.getId(),
                problem.getCondition(),
                problem.getType(),
                problem.getType());

        return res;
    }
}
