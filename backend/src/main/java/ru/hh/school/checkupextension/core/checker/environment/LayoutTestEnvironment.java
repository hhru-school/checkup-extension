package ru.hh.school.checkupextension.core.checker.environment;

import java.io.File;
import java.io.IOException;
import ru.hh.school.checkupextension.core.data.dto.checker.UserSolutionDto;

public class LayoutTestEnvironment extends TestEnvironment {
  private static final String HTML_FILE_NAME = "index.html";
  private static final String CSS_FILE_NAME = "style.css";
  protected static final String PATH_TO_TEMPLATE = String.join(File.separator, ROOT_DIR, "check_layout_template");

  public LayoutTestEnvironment(String workDirectory, String userSolutionDirectory) {
    super(workDirectory, userSolutionDirectory);
  }

  @Override
  protected String getPathToTestScript() {
    return PATH_TO_TEMPLATE;
  }

  @Override
  protected String getCommand() {
    return "npm run comparing";
  }

  @Override
  protected void addUserSolution(UserSolutionDto userSolutionDto) throws IOException {
    writeContentToFile(userSolutionDto.htmlPartSolution(), HTML_FILE_NAME);
    writeContentToFile(userSolutionDto.cssPartSolution(), CSS_FILE_NAME);
  }
}
