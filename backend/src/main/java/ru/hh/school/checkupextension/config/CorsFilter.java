package ru.hh.school.checkupextension.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CorsFilter implements ContainerResponseFilter {

  static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);
  @Override
  public void filter(
      ContainerRequestContext requestContext,
      ContainerResponseContext responseContext
  ) throws IOException {
    LOGGER.info("***** CORS FILTER ***");
    String origin = requestContext.getHeaderString("Origin");
    responseContext.getHeaders().add(
        "Access-Control-Allow-Origin", origin);
    responseContext.getHeaders().add(
        "Access-Control-Allow-Credentials", "true");
    responseContext.getHeaders().add(
        "Access-Control-Allow-Headers",
        "origin, content-type, accept, authorization"
    );
    responseContext.getHeaders().add(
        "Access-Control-Allow-Methods",
        "GET, POST, PUT, DELETE, OPTIONS, HEAD"
    );
  }
}
