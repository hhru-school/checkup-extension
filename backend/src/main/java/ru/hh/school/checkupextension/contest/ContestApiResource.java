package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblem;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;

import java.util.List;

/**
 * Класс, отвечающий за определение и обработку HTTP-запросов, связанных с контестом.
 */
@Path("/contest")
public class ContestApiResource {
    private final ContestService contestService;

    @Inject
    public ContestApiResource(ContestService contestService) {
        this.contestService = contestService;
    }

    /**
     * Метод для получения информации о задаче по ее идентификатору.
     * @param id - id задачи
     * @return - объект типа ContestProblem, представляющий задачу с указанным id
     */
    @GET
    @Path("/problem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ContestProblem getProblem(@PathParam("id")long id) {
        return contestService.getProblem(id);
    }

    /**
     * Метод для получения списка всех задач.
     * @return - список всех задач
     */
    @GET
    @Path("/problem")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProblemEntity> getAllProblems() {
        return contestService.getAllProblems();
    }

    /**
     * Создает новую задачу на основе переданной информации в теле запроса.
     */
    @POST
    @Path("/problem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProblemEntity createProblem(ProblemEntity problem) {
        return contestService.createProblem(problem);
    }

    /**
     * Метод обновляет задачу с указанным id на основе переданной информации в теле запроса.
     */
    @PUT
    @Path("/problem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProblemEntity updateProblem(@PathParam("id") long id, ProblemEntity problem) {
        return contestService.updateProblem(id, problem);
    }

    /**
     * Удаляет задачу с указанным id из базы данных.
     */
    @DELETE
    @Path("/problem/{id}")
    public void deleteProblem(@PathParam("id") long id) {
        contestService.deleteProblem(id);
    }
}
