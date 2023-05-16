package ru.hh.school.checkupextension.admin;

import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.utils.exception.integration.AccessDeniedException;

public class AdminService {

  private final CheckupInteraction checkupInteraction;

  public AdminService(CheckupInteraction checkupInteraction) {
    this.checkupInteraction = checkupInteraction;
  }

  public void checkPermission(String userToken) {
    var user = checkupInteraction.getUserInfo(userToken);

    if (!user.isAdmin())
      throw new AccessDeniedException();
  }
}
