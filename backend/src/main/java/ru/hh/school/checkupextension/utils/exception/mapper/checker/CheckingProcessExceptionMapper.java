package ru.hh.school.checkupextension.utils.exception.mapper.checker;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.checker.CheckingProcessException;

public class CheckingProcessExceptionMapper implements ExceptionMapper<CheckingProcessException> {

  @Override
  public Response toResponse(CheckingProcessException exception) {
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(exception.getMessage())
        .build();
  }
}
