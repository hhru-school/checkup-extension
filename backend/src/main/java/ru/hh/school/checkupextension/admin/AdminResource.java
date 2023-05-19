package ru.hh.school.checkupextension.admin;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestProblemDto;
import ru.hh.school.checkupextension.core.data.entity.Problem;

@Path("/")
public class AdminResource {
  private final AdminService adminService;

  @Inject
  public AdminResource(AdminService adminService) {
    this.adminService = adminService;
  }

  /**
   * Создает новую задачу на основе переданной информации в теле запроса.
   */
  @POST
  @Path("problem")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ContestProblemDto createProblem(@Valid Problem problem) {
    return adminService.createProblem(problem);
  }

  /**
   * Метод обновляет задачу с указанным id на основе переданной информации в теле запроса.
   */
  @PUT
  @Path("problem/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public ContestProblemDto updateProblem(@PathParam("id") long id, Problem problem) {
    return adminService.updateProblem(id, problem);
  }
}
