package ru.hh.school.checkupextension.core;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;

import ru.hh.school.checkupextension.utils.exception.AuthorizedException;

@Path("/")
public class ContestApiResource {
    private final ContestService contestService;

    @Inject
    public ContestApiResource(ContestService contestService) {
        this.contestService = contestService;
    }

    @GET
    @Path("/problem")
    public String getProblem() {
        try {
            return contestService.getProblem();
        }
        catch (AuthorizedException exception) {
            // Как вариант, если пользователь не авторизован, прикидываемся,
            // что такого ресурса нет.
            throw new NotFoundException();
        }
    }
}
