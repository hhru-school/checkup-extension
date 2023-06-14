package ru.hh.school.checkupextension.core.checker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment;
import ru.hh.school.checkupextension.core.checker.environment.TestEnvironment;
import ru.hh.school.checkupextension.core.checker.utils.ProgrammRunner;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;
import ru.hh.school.checkupextension.utils.exception.checker.NotImplementedException;

public class TestingUtility {
  private static final long HEAD_SIZE = 4L;

  private final static ObjectMapper mapper = new ObjectMapper();

  public static TestInfo run(String command, String script) throws IOException {
    var process = ProgrammRunner.run(command, script, LayoutTestEnvironment.ROOT_DIR);
    return readResult(process);
  }

  public static TestEnvironment createEnvironment(UserSolution userSolutionDto) throws IOException {
    TestEnvironment environment;
    var workDirectory = TestEnvironment.createWorkDirectory(userSolutionDto);
    var userSolutionDirectory = TestEnvironment.createUserSolutionDirectory(workDirectory);
    var type = userSolutionDto.type();

    switch (type) {
      case HTML -> throw new NotImplementedException(String.format("Unknown problem's type: %s", type.getTitle()));
      case JS -> environment = new LayoutTestEnvironment(workDirectory, userSolutionDirectory);
      default -> throw new NotImplementedException(String.format("Unknown problem's type: %s", type.getTitle()));
    }

    environment.fillWorkDirectory(userSolutionDto);
    return environment;
  }

  private static TestInfo readResult(Process process) throws JsonProcessingException {
    var raw = readRawData(process);
    return mapper.readValue(raw, TestInfo.class);
  }

  private static String readRawData(Process process) {
    return new BufferedReader(new InputStreamReader(process.getInputStream()))
        .lines()
        .skip(HEAD_SIZE)
        .collect(Collectors.joining("\n"));
  }
}
