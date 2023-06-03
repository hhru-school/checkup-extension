package ru.hh.school.checkupextension.admin;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;

import static ru.hh.school.checkupextension.utils.constant.CookiesName.USER_TOKEN;

@Path("/")
public class AdminResource {
  private final AdminService adminService;

  @Inject
  public AdminResource(AdminService adminService) {
    this.adminService = adminService;
  }

  @POST
  @Path("/problem")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public EditableProblemDto createNewProblem(
      @CookieParam(USER_TOKEN) String userToken,
      EditableProblemDto problemDto
  ) {
    return adminService.createNewProblem(userToken, problemDto);
  }
}
