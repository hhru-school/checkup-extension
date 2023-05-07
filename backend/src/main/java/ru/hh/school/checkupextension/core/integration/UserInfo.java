package ru.hh.school.checkupextension.core.integration;

public class UserInfo {
    public final long UserId;
    public final boolean IsAdmin;

    public UserInfo(long userId, boolean isAdmin) {
        UserId = userId;
        IsAdmin = isAdmin;
    }
}
