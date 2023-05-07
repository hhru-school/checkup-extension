package ru.hh.school.checkupextension.utils.stub;

import org.slf4j.Logger;
import ru.hh.school.checkupextension.core.integration.CheckupInteraction;
import ru.hh.school.checkupextension.core.integration.UserInfo;

import static org.slf4j.LoggerFactory.getLogger;

public class CheckupApiStub implements CheckupInteraction {
    private final static Logger LOGGER = getLogger(CheckupApiStub.class);
    @Override
    public UserInfo getUserInfo(String userToken) {
        LOGGER.info("Get info for user's token: [{}]", userToken);
        return null;
    }

    @Override
    public boolean verifyUserToken(String userToken) {
        LOGGER.info("Verify user's token: [{}]", userToken);
        return false;
    }

    @Override
    public long getUserId(String userToken) {
        LOGGER.info("Get user's id for token: [{}]", userToken);
        return 1L;
    }
}
