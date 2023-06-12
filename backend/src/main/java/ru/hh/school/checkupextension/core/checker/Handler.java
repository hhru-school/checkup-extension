package ru.hh.school.checkupextension.core.checker;

import java.io.File;
import java.io.IOException;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.checker.utils.ProgrammRunner;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;

public class Handler {

  public TestInfo check(ContestSubmissionDto submission) throws IOException {
    TestEnvironment environment = TestEnvironment.initialize(submission);
    return null;
  }

}
