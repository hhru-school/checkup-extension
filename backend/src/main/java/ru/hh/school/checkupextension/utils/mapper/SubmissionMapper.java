package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfo;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfoEntity;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;

import java.time.LocalDateTime;

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
        entity.setRequestDateTime(LocalDateTime.now());
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
                entity.getRequestDateTime(),
                solution.getHtmlPart(),
                solution.getCssPart(),
                solution.getJsPart()
        );
    }

    public static ContestSubmissionShortInfo toContestSubmissionShortInfo(SubmissionShortInfoEntity info) {
        return new ContestSubmissionShortInfo(info.submissionId(), SubmissionsStatus.getTitleBy(info.status()));
    }

    public static ContestSubmissionResult toContestStatusDto(SubmissionEntity submission) {
        var submissionId = submission.getId();
        var status = SubmissionsStatus.getTitleBy(submission.getStatus());
        return new ContestSubmissionResult(submissionId, status);
    }
}
