package ru.hh.school.checkupextension.core.data.controller;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import ru.hh.school.checkupextension.core.data.dto.VerificationDto;
import ru.hh.school.checkupextension.core.data.request.VerificationRequestDto;
import ru.hh.school.checkupextension.core.data.service.VerificationService;

import java.util.List;

/**
 * В данном классе мы использовали аннотации Jersey, такие как @Path и @PathParam, чтобы определить пути запросов
 * и параметры пути. Возвращаемый тип методов теперь Response, в котором мы используем методы класса Response,
 * такие как ok(), noContent() и т.д., чтобы создавать ответы на запросы.
 */
@Path("/api/verifications")
public class VerificationController {
  private final VerificationService verificationService;

  public VerificationController(VerificationService verificationService) {
    this.verificationService = verificationService;
  }

  /**
   * Метод getById() обрабатывает GET-запрос по адресу "/api/verifications/{id}" и возвращает сущность
   * VerificationDto с указанным идентификатором.
   */
  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    VerificationDto verificationDto = verificationService.getById(id);
    return Response.ok(verificationDto).build();
  }

  /**
   * Метод getAll() обрабатывает GET-запрос по адресу "/api/verifications"
   * и возвращает список всех сущностей типа VerificationDto.
   */
  @GET
  public Response getAll() {
    List<VerificationDto> verificationDtos = verificationService.getAll();
    return Response.ok(verificationDtos).build();
  }

  /**
   * Метод create() обрабатывает POST-запрос по адресу "/api/verifications"
   * и создает новую сущность типа VerificationDto на основе полученной в теле запроса информации.
   */
  @POST
  public Response create(VerificationRequestDto requestDto) {
    VerificationDto verificationDto = verificationService.create(requestDto);
    return Response.status(Response.Status.CREATED).entity(verificationDto).build();
  }

  /**
   * Метод update() обрабатывает PUT-запрос по адресу "/api/verifications/{id}"
   * и обновляет существующую сущность типа VerificationDto с указанным идентификатором.
   */
  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, VerificationRequestDto requestDto) {
    VerificationDto verificationDto = verificationService.update(id, requestDto);
    return Response.ok(verificationDto).build();
  }

  /**
   * Метод delete() обрабатывает DELETE-запрос по адресу "/api/verifications/{id}"
   * и удаляет существующую сущность типа VerificationDto с указанным идентификатором.
   */
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    verificationService.delete(id);
    return Response.noContent().build();
  }
}
