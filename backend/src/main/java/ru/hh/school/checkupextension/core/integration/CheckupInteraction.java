package ru.hh.school.checkupextension.core.integration;

public interface CheckupInteraction {
    UserInfo getUserInfo(String userToken);
    boolean verifyUserToken(String userToken);
    long getUserId(String userToken);
}
