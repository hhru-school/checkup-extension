package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.Problem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;

public class ProblemMapper {
    public static ContestProblem toContestProblem(Problem problem) {
        var res = new ContestProblem(
                problem.getId(),
                problem.getTitle(),
                problem.getDescription(),
                problem.getContent(),
                problem.getType(),
                problem.getActive(),
                problem.getTemplate()
        );
        return res;
    }
}
