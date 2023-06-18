package ru.hh.school.checkupextension.utils.exception.mapper.checker;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.checker.ProblemInitializingException;

public class ProblemInitializingExceptionMapper implements ExceptionMapper<ProblemInitializingException> {
  @Override
  public Response toResponse(ProblemInitializingException exception) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(exception.getMessage())
        .build();
  }
}