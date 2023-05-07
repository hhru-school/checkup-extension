package ru.hh.school.checkupextension.utils.exception.mapper.integration;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.integration.ExpiredUserTokenException;

public class ExpiredUserTokenExceptionMapper implements ExceptionMapper<ExpiredUserTokenException> {
    @Override
    public Response toResponse(ExpiredUserTokenException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}