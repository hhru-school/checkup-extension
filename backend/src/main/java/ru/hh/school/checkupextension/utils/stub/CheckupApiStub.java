package ru.hh.school.checkupextension.utils.stub;

import org.slf4j.Logger;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.integration.UserInfo;

import static org.slf4j.LoggerFactory.getLogger;

public class CheckupApiStub implements CheckupInteraction {
  private final static Logger LOGGER = getLogger(CheckupApiStub.class);
  private final static UserInfo defaultAdmin = new UserInfo(0L, true);
  private final static UserInfo defaultUser = new UserInfo(1L, false);

  @Override
  public UserInfo getUserInfo(String userToken) {
    LOGGER.info("Get info for user's token: [{}]", userToken);

    boolean isAdmin = userToken.equalsIgnoreCase("'true'");
    LOGGER.info("User token was converted to [{}]", isAdmin);
    if (isAdmin) {
      return defaultAdmin;
    }
    return defaultUser;
  }

  @Override
  public boolean userHasTimeToSolveProblems(long userId) {
    LOGGER.info("Request user's time for id: [{}]", userId);
    return true;
  }
}
