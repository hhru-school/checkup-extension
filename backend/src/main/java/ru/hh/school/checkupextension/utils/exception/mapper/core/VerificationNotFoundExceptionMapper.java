package ru.hh.school.checkupextension.utils.exception.mapper.core;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.hh.school.checkupextension.utils.exception.core.VerificationNotFoundException;

@Provider
public class VerificationNotFoundExceptionMapper implements ExceptionMapper<VerificationNotFoundException> {
  @Override
  public Response toResponse(VerificationNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND)
        .entity(exception.getMessage())
        .build();
  }
}
