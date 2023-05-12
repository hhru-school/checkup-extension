package ru.hh.school.checkupextension.utils.mapper;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfoDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfo;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;

import java.time.LocalDateTime;

public class SubmissionMapper {

  public static Submission toNewEntity(long userId, ContestSubmissionDto submission) {
    var status = SubmissionsStatus.CHECKED.getCode();
    var problemId = submission.problemId;
    var solution = extractUserSolution(submission);

    var entity = new Submission();
    entity.setProblem(new Problem());
    entity.setUser(userId);
    entity.getProblem().setId(problemId);
    entity.setStatus(status);
    entity.setCreationDateTime(LocalDateTime.now());
    entity.setSolution(solution);

    return entity;
  }

  private static Submission.UserSolution extractUserSolution(ContestSubmissionDto submission) {
    var userSolution = new Submission.UserSolution();
    userSolution.setHtmlPart(submission.htmlPart);
    userSolution.setCssPart(submission.cssPart);
    userSolution.setJsPart(submission.jsPart);

    return userSolution;
  }

  public static ContestSubmissionDto toContestDto(Submission entity) {
    var status = SubmissionsStatus.getTitleBy(entity.getStatus());
    var solution = entity.getSolution();

    return new ContestSubmissionDto(
        entity.getId(),
        entity.getProblem().getId(),
        status,
        entity.getCreationDateTime(),
        solution.getHtmlPart(),
        solution.getCssPart(),
        solution.getJsPart()
    );
  }

  public static ContestSubmissionShortInfoDto toContestSubmissionShortInfo(SubmissionShortInfo info) {
    return new ContestSubmissionShortInfoDto(info.submissionId(), SubmissionsStatus.getTitleBy(info.status()));
  }

  public static ContestSubmissionResultDto toContestStatusDto(Submission submission) {
    var submissionId = submission.getId();
    var status = SubmissionsStatus.getTitleBy(submission.getStatus());
    return new ContestSubmissionResultDto(submissionId, status);
  }
}
