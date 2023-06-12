package ru.hh.school.checkupextension.core.checker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;

public class TestEnvironment {
  private static final String ROOT_DIR = String.join(File.separator, System.getProperty("user.dir"), "backend", "utils");
  private static final String PATH_TO_PROBLEMS_DIRECTORY = String.join(File.separator, ROOT_DIR, "problems");
  private static final String PATH_TO_TEMPLATE = String.join(File.separator, ROOT_DIR, "check_layout_template");

  private static final String TEST_SCRIPT_NAME = "test.js";
  private static final String HTML_FILE_NAME = "index.html";
  private static final String CSS_FILE_NAME = "style.css";

  private final String workDirectory;
  private final String userSolutionDirectory;
  private String pathToTestScript;

  private TestEnvironment(String workDirectory, String userSolutionDirectory) {
    this.workDirectory = workDirectory;
    this.userSolutionDirectory = userSolutionDirectory;
  }

  public String getPathToTestScript() {
    return pathToTestScript;
  }

  public static TestEnvironment initialize(ContestSubmissionDto submission) throws IOException {
    var workDirectory = createWorkDirectory(submission);
    var userSolutionDirectory = createUserSolutionDirectory(workDirectory);
    var environment = new TestEnvironment(workDirectory, userSolutionDirectory);
    environment.fillWorkDirectory(submission);

    return environment;
  }

  private static String createWorkDirectory(ContestSubmissionDto submission) throws IOException {
    var workDirectory = getPathToWorkDirectory(submission);
    var pathToWorkDirectory = Paths.get(workDirectory);
    Files.createDirectories(pathToWorkDirectory);
    return workDirectory;
  }

  private static String getPathToWorkDirectory(ContestSubmissionDto submission) {
    String problemId = String.valueOf(submission.problemId);
    String submissionId = String.valueOf(submission.id);
    return String.join(File.separator, PATH_TO_PROBLEMS_DIRECTORY, problemId, "submissions", submissionId);
  }

  private static String createUserSolutionDirectory(String workDirectory) throws IOException {
    var userSolutionDirectory = String.join(File.separator, workDirectory, "solution");
    var pathToDir = Paths.get(userSolutionDirectory);
    Files.createDirectory(pathToDir);
    return  userSolutionDirectory;
  }

  private void fillWorkDirectory(ContestSubmissionDto submission) throws IOException {
    addTestFile();
    addUserSolution(submission);
  }

  private void addTestFile() throws IOException {
    var inputFilePath = Paths.get(PATH_TO_TEMPLATE);
    this.pathToTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
    var outputFilePath = Paths.get(pathToTestScript);

    Files.copy(inputFilePath, outputFilePath);
  }

  private void addUserSolution(ContestSubmissionDto submissionDto) throws IOException {
    writeContentToFile(submissionDto.htmlPart, HTML_FILE_NAME);
    writeContentToFile(submissionDto.cssPart, CSS_FILE_NAME);
  }

  private void writeContentToFile(String content, String fileName) throws IOException {
    var file = String.join(File.separator, userSolutionDirectory, fileName);
    var pathToFile = Paths.get(file);
    Files.writeString(pathToFile, content);
  }
}
