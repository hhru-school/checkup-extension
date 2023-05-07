package ru.hh.school.checkupextension.core.integration;

public interface CheckupInteraction {
    UserInfo getUserInfo(String userToken);
    boolean userHasTimeToSolveProblems(long userId);
}
