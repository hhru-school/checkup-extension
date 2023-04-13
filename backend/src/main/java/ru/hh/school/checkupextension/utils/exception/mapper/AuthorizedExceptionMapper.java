package ru.hh.school.checkupextension.utils.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import ru.hh.school.checkupextension.utils.exception.AuthorizedException;

@Provider
public class AuthorizedExceptionMapper implements ExceptionMapper<AuthorizedException> {
    @Override
    public Response toResponse(AuthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
