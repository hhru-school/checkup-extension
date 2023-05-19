package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResultDto;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionShortInfoDto;

import java.util.List;

import static ru.hh.school.checkupextension.utils.constant.CookiesName.USER_TOKEN;

/**
 * Класс, отвечающий за определение и обработку HTTP-запросов, связанных с контестом.
 */
@Path("/")
public class ContestResource {
  private final ContestService contestService;

  @Inject
  public ContestResource(ContestService contestService) {
    this.contestService = contestService;
  }

  /**
   * Метод для получения информации о задаче по ее идентификатору.
   * @param id - id задачи
   * @return - объект типа ContestProblem, представляющий задачу с указанным id
   */
  @GET
  @Path("problem/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ContestProblemDto getProblem(@PathParam("id")long id) {
    return contestService.getProblem(id);
  }

  @POST
  @Path("/submissions")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ContestSubmissionDto sendSubmission(
      @CookieParam(USER_TOKEN) String userToken,
      ContestSubmissionDto submission
  ) {
    return contestService.createSubmission(userToken, submission);
  }

  @GET
  @Path("/submissions/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ContestSubmissionDto getSubmission(
      @CookieParam(USER_TOKEN) String userToken,
      @PathParam("id") long submissionId
  ) {
    return contestService.getSubmission(userToken, submissionId);
  }

  @GET
  @Path("/submissions/task/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<ContestSubmissionShortInfoDto> getUserSubmissionsInfo(
      @CookieParam(USER_TOKEN) String userToken,
      @PathParam("id") long problemId
  ) {
    return contestService.getUserSubmissionsInfo(userToken, problemId);
  }

  @GET
  @Path("/submissions/{id}/status")
  @Produces(MediaType.APPLICATION_JSON)
  public ContestSubmissionResultDto getSubmissionStatus(@PathParam("id") long submissionId) {
    return contestService.getSubmissionStatus(submissionId);
  }
}
