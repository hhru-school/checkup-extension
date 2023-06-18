package ru.hh.school.checkupextension.core.checker.register;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import ru.hh.school.checkupextension.core.checker.TestingUtility;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.COMMAND;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.CSS_FILE_NAME;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.HTML_FILE_NAME;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.PATH_TO_TEMPLATE;
import static ru.hh.school.checkupextension.core.checker.environment.TestEnvironment.TEST_SCRIPT_NAME;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public class ScreenshotsCreator extends ProblemRegister {
  protected String pathToFinalTestScript;

  private static final ScreenshotsCreator screenshotsCreator = new ScreenshotsCreator();

  public static void createScreenshot(Problem addedProblem) {
    screenshotsCreator.register(addedProblem);
  }

  public static void updateScreenshot(Problem problem) {
    screenshotsCreator.update(problem);
  }

  @Override
  protected void save(Problem problem, String workDir, String solutionDir) {
    var snapshotsDir = String.join(File.separator, workDir, "__snapshots__");

    try {
      Files.createDirectories(Paths.get(solutionDir));
      Files.createDirectories(Paths.get(snapshotsDir));

      saveSolutionToFiles(problem, solutionDir);
      addTestFile(workDir);
      TestingUtility.run(COMMAND, pathToFinalTestScript);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void update(Problem problem, String workDir, String solutionDir) {
    try {
      saveSolutionToFiles(problem, solutionDir);
      cleanSnapshots(workDir);
      pathToFinalTestScript = String.join(File.separator, workDir, TEST_SCRIPT_NAME);
      TestingUtility.run(COMMAND, pathToFinalTestScript);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void cleanSnapshots(String workDir) {
    var snapshotsDir = String.join(File.separator, workDir, "__snapshots__");
    var path = Paths.get(snapshotsDir);
    Arrays.stream(path.toFile().listFiles()).forEach(File::delete);
  }

  private void saveSolutionToFiles(Problem problem, String solutionDir) throws IOException {
    var solution = problem.getReferenceSolution();
    var htmlFile = Paths.get(solutionDir, HTML_FILE_NAME);
    writeContentToFile(solution.getHtmlPart(), htmlFile);
    var cssFile = Paths.get(solutionDir, CSS_FILE_NAME);
    writeContentToFile(solution.getCssPart(), cssFile);
  }

  private void addTestFile(String workDirectory) throws IOException {
    var inputFilePath = Paths.get(PATH_TO_TEMPLATE);
    var template = Files.readString(inputFilePath);
    var contentOfScript = template.replaceFirst("path_to_snapshots_replace_mark", "'__snapshots__'");
    pathToFinalTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
    var outputFilePath = Paths.get(pathToFinalTestScript);
    writeContentToFile(contentOfScript, outputFilePath);
  }
}
