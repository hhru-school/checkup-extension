package ru.hh.school.checkupextension.utils.exception.mapper.core;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;

public class SubmissionNotFoundExceptionMapper implements ExceptionMapper<SubmissionNotFoundException> {
  @Override
  public Response toResponse(SubmissionNotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND)
        .entity(exception.getMessage())
        .build();
  }
}
