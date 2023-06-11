package ru.hh.school.checkupextension.core.checker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ru.hh.school.checkupextension.core.checker.data.TestInfo;
import ru.hh.school.checkupextension.core.data.dto.contest.ContestSubmissionDto;

public class Handler {
  private final String root = String.join(File.separator, System.getProperty("user.dir"), "backend", "utils");
  private final String pathToProblemsDirectory = String.join(File.separator, root, "problems");
  private final String pathToTemplate = String.join(File.separator, root, "check_layout_template");

  private String workDirectory;
  private String userSolutionDirectory;

  public TestInfo check(ContestSubmissionDto submission) throws IOException {
//    System.out.println("root: " + root);
//    System.out.println("problems directory: " + pathToProblemsDirectory);
//    System.out.println("work directory: " + workDirectory);
    prepareWorkDirectory(submission);
    return null;
  }

  private String getPathToWorkDirectory(ContestSubmissionDto submission) {
    String problemId = String.valueOf(submission.problemId);
    String submissionId = String.valueOf(submission.id);
    return String.join(File.separator, pathToProblemsDirectory, problemId, "submissions", submissionId);
  }

  private void prepareWorkDirectory(ContestSubmissionDto submission) throws IOException {
    workDirectory = getPathToWorkDirectory(submission);
    Files.createDirectories(Paths.get(workDirectory));

    fillWorkDirectory(submission);
  }

  private void fillWorkDirectory(ContestSubmissionDto submission) throws IOException {
    addTestFile();
    addUserSolution(submission);
  }

  private void addTestFile() throws IOException {
    var inputFilePath = Paths.get(pathToTemplate);
    var outputFile = String.join(File.separator, workDirectory, "test.js");
    var outputFilePath = Paths.get(outputFile);

    Files.copy(inputFilePath, outputFilePath);
  }

  private void addUserSolution(ContestSubmissionDto submissionDto) throws IOException {
    prepareUserSolutionDirectory();
    writeContentToFile(submissionDto.htmlPart, "index.html");
    writeContentToFile(submissionDto.cssPart,  "style.css");
  }

  private void prepareUserSolutionDirectory() throws IOException {
    userSolutionDirectory = String.join(File.separator, workDirectory, "solution");
    var pathToDir = Paths.get(userSolutionDirectory);
    Files.createDirectory(pathToDir);
  }

  private void writeContentToFile(String content, String fileName) throws IOException {
    var file = String.join(File.separator, userSolutionDirectory, fileName);
    var pathToFile = Paths.get(file);
    Files.writeString(pathToFile, content);
  }
}
