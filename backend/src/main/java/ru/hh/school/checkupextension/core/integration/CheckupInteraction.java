package ru.hh.school.checkupextension.core.integration;

import ru.hh.school.checkupextension.core.integration.data.UserInfo;

public interface CheckupInteraction {
  UserInfo getUserInfo(String userToken);

  boolean userHasTimeToSolveProblems(long userId);
}
