package ru.hh.school.checkupextension.core.checker.environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ru.hh.school.checkupextension.core.checker.TestingUtility;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;

public abstract class TestEnvironment {
  public static final String ROOT_DIR = String.join(File.separator, System.getProperty("user.dir"), "backend", "utils");
  protected static final String PATH_TO_PROBLEMS_DIRECTORY = String.join(File.separator, ROOT_DIR, "problems");

  protected static final String TEST_SCRIPT_NAME = "test.js";

  protected final String workDirectory;
  protected final String userSolutionDirectory;
  protected String pathToFinalTestScript;

  public TestEnvironment(String workDirectory, String userSolutionDirectory) {
    this.workDirectory = workDirectory;
    this.userSolutionDirectory = userSolutionDirectory;
  }

  public static String createWorkDirectory(UserSolution submission) throws IOException {
    var workDirectory = getPathToWorkDirectory(submission);
    var pathToWorkDirectory = Paths.get(workDirectory);
    Files.createDirectories(pathToWorkDirectory);
    return workDirectory;
  }

  private static String getPathToWorkDirectory(UserSolution userSolutionDto) {
    String problemId = String.valueOf(userSolutionDto.problemId());
    String submissionId = String.valueOf(userSolutionDto.submissionId());
    return String.join(File.separator, PATH_TO_PROBLEMS_DIRECTORY, problemId, "submissions", submissionId);
  }

  public static String createUserSolutionDirectory(String workDirectory) throws IOException {
    var userSolutionDirectory = String.join(File.separator, workDirectory, "solution");
    var pathToDir = Paths.get(userSolutionDirectory);
    Files.createDirectory(pathToDir);
    return userSolutionDirectory;
  }

  public void fillWorkDirectory(UserSolution submission) throws IOException {
    addTestFile();
    addUserSolution(submission);
  }

  private void addTestFile() throws IOException {
    var inputFilePath = Paths.get(getPathToTestScript());
    this.pathToFinalTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
    var outputFilePath = Paths.get(pathToFinalTestScript);

    Files.copy(inputFilePath, outputFilePath);
  }

  protected abstract String getPathToTestScript();

  protected abstract void addUserSolution(UserSolution submissionDto) throws IOException;

  protected void writeContentToFile(String content, String fileName) throws IOException {
    var file = String.join(File.separator, userSolutionDirectory, fileName);
    var pathToFile = Paths.get(file);
    Files.writeString(pathToFile, content);
  }

  public TestInfo run() throws IOException {
    return TestingUtility.run(getCommand(), pathToFinalTestScript);
  }

  protected abstract String getCommand();
}
