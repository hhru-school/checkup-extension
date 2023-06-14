package ru.hh.school.checkupextension.core.checker;

import java.io.IOException;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolutionDto;
import ru.hh.school.checkupextension.utils.exception.checker.CheckingProcessException;

public class Checker {
  public TestInfo check(UserSolutionDto userSolutionDto) {
    try {
      return TestingUtility.createEnvironment(userSolutionDto).run();
    }
    catch (IOException exception) {
      throw new CheckingProcessException();
    }
  }
}
