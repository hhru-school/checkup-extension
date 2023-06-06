package ru.hh.school.checkupextension.admin;

import jakarta.inject.Inject;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import ru.hh.school.checkupextension.core.data.dto.PaginationResultDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemDto;
import ru.hh.school.checkupextension.core.data.dto.admin.EditableProblemInfoDto;
import static ru.hh.school.checkupextension.utils.constant.CookiesName.USER_TOKEN;

@Path("/")
public class AdminResource {
  private final AdminService adminService;

  @Inject
  public AdminResource(AdminService adminService) {
    this.adminService = adminService;
  }

  @GET
  @Path("/problems/all")
  @Produces(MediaType.APPLICATION_JSON)
  public PaginationResultDto<EditableProblemInfoDto> getAllProblems(
      @CookieParam((USER_TOKEN)) String userToken,
      @QueryParam("page") @DefaultValue("1") @Min(1) int pageNumber,
      @QueryParam("size") @DefaultValue("20") @Min(1) int pageSize
  ) {
    return adminService.getAllProblemsToEdit(userToken, pageNumber, pageSize);
  }

  @GET
  @Path("/problem/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public EditableProblemDto getExistProblem(
      @CookieParam(USER_TOKEN) String userToken,
      @PathParam("id") Long problemId
  ) {
    return adminService.getProblemToEdit(userToken, problemId);
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
