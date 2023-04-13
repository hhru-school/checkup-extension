package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/contest")
public class ContestApiResource {
    private final ContestService contestService;

    @Inject
    public ContestApiResource(ContestService contestService) {
        this.contestService = contestService;
    }

    @GET
    @Path("/problem")
    public String getProblem() {
        return contestService.getProblem();
    }
}
