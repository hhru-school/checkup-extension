package ru.hh.school.checkupextension.core.checker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.checker.utils.ProgrammRunner;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;

public class Handler {
  private static final String CMD = "npm run comparing";
  private static final long HEAD_SIZE = 4L;

  private final ObjectMapper mapper = new ObjectMapper();

  public TestInfo check(ContestSubmissionDto submission) throws IOException {
    TestEnvironment environment = TestEnvironment.initialize(submission);
    String arg = environment.getPathToTestScript();
    var process = ProgrammRunner.run(CMD, arg, TestEnvironment.ROOT_DIR);
    return readResult(process);
  }

  public TestInfo readResult(Process process) throws JsonProcessingException {
    var raw = readRawData(process);
    return mapper.readValue(raw, TestInfo.class);
  }

  private String readRawData(Process process) {
    return new BufferedReader(new InputStreamReader(process.getInputStream()))
        .lines()
        .skip(HEAD_SIZE)
        .collect(Collectors.joining("\n"));
  }
}
