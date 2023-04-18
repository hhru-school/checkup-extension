package ru.hh.school.checkupextension.core.data.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import ru.hh.school.checkupextension.core.data.dto.ProblemDto;
import ru.hh.school.checkupextension.core.data.request.ProblemRequestDto;
import ru.hh.school.checkupextension.core.data.service.ProblemService;

import java.util.List;

/**
 * В данном классе мы использовали аннотации Jersey, такие как @Path и @PathParam, чтобы определить пути запросов
 * и параметры пути. Возвращаемый тип методов теперь Response, в котором мы используем методы класса Response,
 * такие как ok(), noContent() и т.д., чтобы создавать ответы на запросы.
 */
@Path("/api/problems")
public class ProblemResource {
  private final ProblemService problemService;

  public ProblemResource(ProblemService problemService) {
    this.problemService = problemService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/problems/{id}" и возвращает сущность
   * ProblemDto с указанным идентификатором.
   */
  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    ProblemDto problemDto = problemService.getById(id);
    return Response.ok(problemDto).build();
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/problems"
   * и возвращает список всех сущностей типа ProblemDto.
   */
  @GET
  public Response getAll() {
    List<ProblemDto> problemDtos = problemService.getAll();
    return Response.ok(problemDtos).build();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/problems"
   * и создает новую сущность типа ProblemDto на основе полученной в теле запроса информации.
   */
  @POST
  public Response create(ProblemRequestDto requestDto) {
    ProblemDto problemDto = problemService.create(requestDto);
    return Response.status(Response.Status.CREATED).entity(problemDto).build();
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/problems/{id}"
   * и обновляет существующую сущность типа ProblemDto с указанным идентификатором.
   */
  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, ProblemRequestDto requestDto) {
    ProblemDto problemDto = problemService.update(id, requestDto);
    return Response.ok(problemDto).build();
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/problems/{id}"
   * и удаляет существующую сущность типа ProblemDto с указанным идентификатором.
   */
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    problemService.delete(id);
    return Response.noContent().build();
  }
}
