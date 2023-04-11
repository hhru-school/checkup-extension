package ru.hh.school.checkupextension.core;

import jakarta.inject.Inject;
import ru.hh.school.checkupextension.utils.exception.AuthorizedException;

public class ContestService {
    private final CheckupInteraction checkupInteraction;

    @Inject
    public ContestService(CheckupInteraction checkupInteraction) {
        this.checkupInteraction = checkupInteraction;
    }

    public String getProblem() throws AuthorizedException{
        if (!checkupInteraction.verifyUserToken(""))
            throw new AuthorizedException(); // Через свои исключения избавляемся от привязки к определённым технологиям

        return "Problem's statement";
    }
}
