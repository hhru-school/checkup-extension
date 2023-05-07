package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;

public class SubmissionMapper {

    public static SubmissionEntity toNewEntity(long userId, ContestSubmission submission) {
        var status = SubmissionsStatus.CHECKED.getCode();
        var problemId = submission.problemId;
        var solution = extractUserSolution(submission);

        var entity = new SubmissionEntity();
        entity.setProblem(new ProblemEntity());
        entity.setUser(userId);
        entity.getProblem().setId(problemId);
        entity.setStatus(status);
        entity.setSolution(solution);

        return entity;
    }

    private static SubmissionEntity.UserSolution extractUserSolution(ContestSubmission submission) {
        var userSolution = new SubmissionEntity.UserSolution();
        userSolution.setHtmlPart(submission.htmlPart);
        userSolution.setCssPart(submission.cssPart);
        userSolution.setJsPart(submission.jsPart);

        return userSolution;
    }

    public static ContestSubmission toContestDto(SubmissionEntity entity) {
        var status = SubmissionsStatus.getTitleBy(entity.getStatus());
        var solution = entity.getSolution();

        return new ContestSubmission(
                entity.getId(),
                entity.getProblem().getId(),
                status,
                solution.getHtmlPart(),
                solution.getCssPart(),
                solution.getJsPart()
        );
    }

    public static ContestSubmissionResult toContestStatusDto(SubmissionEntity submission) {
        var submissionId = submission.getId();
        var status = SubmissionsStatus.getTitleBy(submission.getStatus());
        return new ContestSubmissionResult(submissionId, status);
    }
}
