package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;

@Path("/contest")
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
}
