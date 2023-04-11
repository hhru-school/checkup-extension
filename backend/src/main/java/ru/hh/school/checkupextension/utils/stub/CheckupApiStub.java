package ru.hh.school.checkupextension.utils.stub;

import ru.hh.school.checkupextension.core.CheckupInteraction;

public class CheckupApiStub implements CheckupInteraction {
    public boolean verifyUserToken(String userToken) {
        return true;
    }
}
