package ru.hh.school.checkupextension.contest;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import ru.hh.school.checkupextension.core.CheckupInteraction;

public class ContestService {

    private final static Logger LOGGER = getLogger(ContestService.class);
    private final CheckupInteraction checkupInteraction;

    @Inject
    public ContestService(CheckupInteraction checkupInteraction) {
        this.checkupInteraction = checkupInteraction;
    }

    public String getProblem() {
        if (checkupInteraction.verifyUserToken(""))
            throw new NotAuthorizedException("");

        return "Problem's statement";
    }
}
