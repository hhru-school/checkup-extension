package ru.hh.school.checkupextension.core;

public interface CheckupInteraction {
    boolean verifyUserToken(String userToken);
    long getUserId(String userToken);
}
