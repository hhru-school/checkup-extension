package ru.hh.school.checkupextension.admin;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.integration.data.UserInfo;
import ru.hh.school.checkupextension.core.repository.ProblemRepository;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

public class AdminServiceTest {
  private final static String NOT_ADMIN_TOKEN = "not admin";
  private final static String ADMIN_TOKEN = "admin";
  private static AdminService adminService;

  @BeforeAll
  static void init() {
    var checkupInteraction = mock(CheckupInteraction.class);
    when(checkupInteraction.getUserInfo(ADMIN_TOKEN))
        .thenReturn(new UserInfo(0, true));
    when(checkupInteraction.getUserInfo(NOT_ADMIN_TOKEN))
        .thenReturn(new UserInfo(1, false));

    var problemRepository = Mockito.mock(ProblemRepository.class);
    when(problemRepository.create(any(Problem.class)))
        .thenAnswer(i ->
        {
          var problem = (Problem)i.getArgument(0);
          problem.setId(1L);
          return problem;
        });

    adminService = new AdminService(checkupInteraction, problemRepository);
  }

  @Test
  void shouldThrowExceptionIfNotAdmin() {
    assertThrows(AccessDeniedException.class,
                 () -> adminService.checkPermission(NOT_ADMIN_TOKEN));
  }

  @Test
  void shouldGiveAccessForAdmin() {
    assertDoesNotThrow(() -> adminService.checkPermission(ADMIN_TOKEN));
  }
}
