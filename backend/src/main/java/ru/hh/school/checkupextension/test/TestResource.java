package ru.hh.school.checkupextension.test;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {
    private final TestService testService;

    @Inject
    public TestResource(TestService testService) {
        this.testService = testService;
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public TestDto test() {
        testService.saveTestData(new TestEntity("I'm alive!"));
        TestEntity testEntity = testService.getTestData();
        return new TestDto(testEntity.getTestData());
    }
}
