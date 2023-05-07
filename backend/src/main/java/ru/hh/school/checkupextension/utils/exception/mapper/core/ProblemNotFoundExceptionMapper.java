package ru.hh.school.checkupextension.utils.exception.mapper.core;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import ru.hh.school.checkupextension.utils.exception.core.ProblemNotFoundException;

public class ProblemNotFoundExceptionMapper implements ExceptionMapper<ProblemNotFoundException> {

    @Override
    public Response toResponse(ProblemNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
    }
}
