package ru.hh.school.checkupextension.core.checker.environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolution;

public class LayoutTestEnvironment extends TestEnvironment {

  public static final String HTML_FILE_NAME = "index.html";
  public static final String CSS_FILE_NAME = "style.css";
  public static final String COMMAND = "npm run comparing";
  public static final String PATH_TO_TEMPLATE = String.join(File.separator, ROOT_DIR, "check_layout_template");

  public LayoutTestEnvironment(String workDirectory, String userSolutionDirectory) {
    super(workDirectory, userSolutionDirectory);
  }

  @Override
  protected void addTestFile() throws IOException {
    var inputFilePath = Paths.get(PATH_TO_TEMPLATE);
    var template = Files.readString(inputFilePath);
    var contentOfScript = template.replaceFirst("path_to_snapshots_replace_mark", "'..', '..', '__snapshots__'");
    pathToFinalTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
    var outputFilePath = Paths.get(pathToFinalTestScript);
    Files.writeString(outputFilePath, contentOfScript);
  }

  @Override
  protected String getCommand() {
    return COMMAND;
  }

  @Override
  protected void addUserSolution(UserSolution userSolutionDto) throws IOException {
    writeContentToFile(userSolutionDto.htmlPartSolution(), HTML_FILE_NAME);
    writeContentToFile(userSolutionDto.cssPartSolution(), CSS_FILE_NAME);
  }
}
