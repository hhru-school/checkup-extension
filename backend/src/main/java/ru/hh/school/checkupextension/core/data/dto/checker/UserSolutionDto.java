package ru.hh.school.checkupextension.core.data.dto.checker;

import ru.hh.school.checkupextension.core.data.enums.ProblemType;

public record UserSolutionDto(
    Long submissionId,
    Long problemId,
    ProblemType type,
    String htmlPartSolution, String cssPartSolution, String jsPartSolution) {
}
