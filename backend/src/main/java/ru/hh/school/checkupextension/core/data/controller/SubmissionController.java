package ru.hh.school.checkupextension.core.data.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import ru.hh.school.checkupextension.core.data.dto.SubmissionDto;
import ru.hh.school.checkupextension.core.data.request.SubmissionRequestDto;
import ru.hh.school.checkupextension.core.data.service.SubmissionService;

import java.util.List;

/**
 * В данном классе мы использовали аннотации Jersey, такие как @Path и @PathParam, чтобы определить пути запросов
 * и параметры пути. Возвращаемый тип методов теперь Response, в котором мы используем методы класса Response,
 * такие как ok(), noContent() и т.д., чтобы создавать ответы на запросы.
 */
@Path("/api/submissions")
public class SubmissionController {
  private final SubmissionService submissionService;

  public SubmissionController(SubmissionService submissionService) {
    this.submissionService = submissionService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/submissions/{id}" и возвращает сущность
   * SubmissionDto с указанным идентификатором.
   */
  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    SubmissionDto submissionDto = submissionService.getById(id);
    return Response.ok(submissionDto).build();
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/submissions"
   * и возвращает список всех сущностей типа SubmissionDto.
   */
  @GET
  public Response getAll() {
    List<SubmissionDto> submissionDtos = submissionService.getAll();
    return Response.ok(submissionDtos).build();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/submissions"
   * и создает новую сущность типа SubmissionDto на основе полученной в теле запроса информации.
   */
  @POST
  public Response create(SubmissionRequestDto requestDto) {
    SubmissionDto submissionDto = submissionService.create(requestDto);
    return Response.status(Response.Status.CREATED).entity(submissionDto).build();
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/submissions/{id}"
   * и обновляет существующую сущность типа SubmissionDto с указанным идентификатором.
   */
  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, SubmissionRequestDto requestDto) {
    SubmissionDto submissionDto = submissionService.update(id, requestDto);
    return Response.ok(submissionDto).build();
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/submissions/{id}"
   * и удаляет существующую сущность типа SubmissionDto с указанным идентификатором.
   */
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    submissionService.delete(id);
    return Response.noContent().build();
  }
}
