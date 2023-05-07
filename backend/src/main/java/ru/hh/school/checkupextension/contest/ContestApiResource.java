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
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmission;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionResult;

import static ru.hh.school.checkupextension.utils.constant.CookiesName.USER_TOKEN;

@Path("/")
public class ContestApiResource {

    private final ContestService contestService;

    @Inject
    public ContestApiResource(ContestService contestService) {
        this.contestService = contestService;
    }

    @GET
    @Path("/problem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContestProblem getProblem(@PathParam("id")long id) {
        return contestService.getProblem(id);
    }

    @POST
    @Path("/submissions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ContestSubmission sendSubmission(@CookieParam(USER_TOKEN) String userToken,
                                            ContestSubmission submission) {
        return contestService.createSubmission(userToken, submission);
    }

    @GET
    @Path("/submissions/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContestSubmission getSubmission(@CookieParam(USER_TOKEN) String userToken,
                                           @PathParam("id") long submissionId) {
        return contestService.getSubmission(userToken, submissionId);
    }

    @GET
    @Path("/submissions/status/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContestSubmissionResult getSubmissionStatus(@CookieParam(USER_TOKEN) String userToken,
                                                       @PathParam("id") long submissionId) {
        return contestService.getSubmissionStatus(userToken, submissionId);
    }
}
