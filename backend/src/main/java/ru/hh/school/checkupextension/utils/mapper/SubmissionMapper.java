package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;

public class SubmissionMapper {

    public SubmissionEntity toEntity(long userId, ContestSubmission submission) {
        var status = SubmissionsStatus.valueOf(submission.status).getCode();
        var solution = extractUserSolution(submission);

        var entity = new SubmissionEntity();
        entity.setStatus(status);
        entity.setUser(userId);
        entity.getProblem().setId(submission.problemId);
        entity.setSolution(solution);

        return entity;
    }

    private SubmissionEntity.UserSolution extractUserSolution(ContestSubmission submission) {
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
}
