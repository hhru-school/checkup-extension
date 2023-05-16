package ru.hh.school.checkupextension.admin;

import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.integration.UserInfo;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

public class AdminApiTest {
  private final static String NOT_ADMIN_TOKEN = "not admin";
  private final static String ADMIN_TOKEN = "admin";
  private static AdminService adminService;

  @BeforeAll
  static void init() {
    CheckupInteraction checkupInteraction = Mockito.mock(CheckupInteraction.class);
    Mockito.when(checkupInteraction.getUserInfo(ADMIN_TOKEN))
        .thenReturn(new UserInfo(0, true));
    Mockito.when(checkupInteraction.getUserInfo(NOT_ADMIN_TOKEN))
        .thenReturn(new UserInfo(1, false));

    adminService = new AdminService(checkupInteraction);
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
