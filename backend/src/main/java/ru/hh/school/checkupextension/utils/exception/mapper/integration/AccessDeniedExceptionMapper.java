package ru.hh.school.checkupextension.utils.exception.mapper.integration;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
  @Override
  public Response toResponse(AccessDeniedException exception) {
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
