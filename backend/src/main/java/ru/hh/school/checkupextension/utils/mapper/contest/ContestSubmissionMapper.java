package ru.hh.school.checkupextension.utils.mapper.contest;

import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfoDto;
import ru.hh.school.checkupextension.core.data.entity.JsonContainer;
import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfo;
import ru.hh.school.checkupextension.core.data.enums.SubmissionsStatus;
import ru.hh.school.checkupextension.utils.builder.SubmissionBuilder;

public class ContestSubmissionMapper {

  public static Submission toNewEntity(long userId, ContestSubmissionDto submission) {
    var status = SubmissionsStatus.CHECKED.getCode();
    var problemId = submission.problemId;
    var solution = extractUserSolution(submission);

    return SubmissionBuilder.buildSubmission(userId, problemId, status, solution);
  }

  private static JsonContainer extractUserSolution(ContestSubmissionDto submission) {
    return JsonContainer.fill(
        submission.htmlPart,
        submission.cssPart,
        submission.jsPart
    );
  }

  public static ContestSubmissionDto toContestDto(Submission entity) {
    var status = SubmissionsStatus.getTitleBy(entity.getStatus());
    var solution = entity.getUserSolution();

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
