package ru.hh.school.checkupextension.core.checker;

import java.io.IOException;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolutionDto;

public class Checker {
  public TestInfo check(UserSolutionDto userSolutionDto) throws IOException {
    return TestingUtility.createEnvironment(userSolutionDto).run();
  }
}
