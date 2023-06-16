package ru.hh.school.checkupextension.core.checker.creator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import ru.hh.school.checkupextension.core.checker.TestingUtility;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public class ScreenshotsCreator extends SolutionSaver {
  private static final String HTML_FILE_NAME = "index.html";
  private static final String CSS_FILE_NAME = "style.css";
  protected static final String TEST_SCRIPT_NAME = "test.js";
  public static final String ROOT_DIR = String.join(File.separator, System.getProperty("user.dir"), "backend", "utils");
  protected static final String PATH_TO_TEMPLATE = String.join(File.separator, ROOT_DIR, "check_layout_template");

  protected String pathToFinalTestScript;

  private static final ScreenshotsCreator screenshotsCreator = new ScreenshotsCreator();

  public static void createScreenshot(Problem addedProblem) {
    screenshotsCreator.saveSolution(addedProblem);
  }

  @Override
  protected void save(Problem problem, String workDir, String solutionDir) {
    var snapshotsDir = String.join(File.separator, workDir, "__snapshots__");

    try {
      Files.createDirectories(Paths.get(solutionDir));
      Files.createDirectories(Paths.get(snapshotsDir));

      saveSolution(problem, solutionDir);
      addTestFile(workDir);
      TestingUtility.run("npm run comparing", pathToFinalTestScript);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected void saveSolution(Problem problem, String solutionDir) throws IOException {
    var solution = problem.getReferenceSolution();
    var htmlFile = Paths.get(solutionDir, HTML_FILE_NAME);
    writeContentToFile(solution.getHtmlPart(), htmlFile);
    var cssFile = Paths.get(solutionDir, CSS_FILE_NAME);
    writeContentToFile(solution.getCssPart(), cssFile);
  }

  protected void writeContentToFile(String content, Path file) throws IOException {
    Files.writeString(file, content);
  }

  private void addTestFile(String workDirectory) throws IOException {
    var inputFilePath = Paths.get(PATH_TO_TEMPLATE);
    var template = Files.readString(inputFilePath);
    var contentOfScript = template.replaceFirst("path_to_snapshots_replace_mark", "__snapshots__");
    //  '..', '..', '__snapshots__'
    pathToFinalTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
    var outputFilePath = Paths.get(pathToFinalTestScript);

    if (Files.exists(outputFilePath)) {
      Files.delete(outputFilePath);
    }
    ;

    Files.writeString(outputFilePath, contentOfScript);
  }
}
