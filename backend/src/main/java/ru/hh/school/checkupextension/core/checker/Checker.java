package ru.hh.school.checkupextension.core.checker;

import java.io.IOException;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;
import ru.hh.school.checkupextension.utils.exception.checker.CheckingProcessException;

public class Checker {
  public TestInfo check(UserSolution userSolutionDto) {
    try {
      return TestingUtility.createEnvironment(userSolutionDto).run();
    }
    catch (IOException exception) {
      throw new CheckingProcessException();
    }
  }
}
