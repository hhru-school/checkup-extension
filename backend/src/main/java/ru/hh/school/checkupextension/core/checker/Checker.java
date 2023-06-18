package ru.hh.school.checkupextension.core.checker;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;
import ru.hh.school.checkupextension.utils.exception.checker.CheckingProcessException;

public class Checker {
  private final static Logger LOGGER = LoggerFactory.getLogger(Checker.class);
  public TestInfo check(UserSolution userSolutionDto) {
    try {
      return TestingUtility.createEnvironment(userSolutionDto).run();
    }
    catch (IOException exception) {
      LOGGER.error("Ошибка в процессе обработки решения. Подробнее {}", exception.getMessage());
      throw new CheckingProcessException();
    }
  }
}
