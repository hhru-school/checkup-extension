package ru.hh.school.checkupextension.core.checker.register;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.checkupextension.core.checker.TestingUtility;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.COMMAND;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.CSS_FILE_NAME;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.HTML_FILE_NAME;
import static ru.hh.school.checkupextension.core.checker.environment.LayoutTestEnvironment.PATH_TO_TEMPLATE;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.utils.exception.checker.ProblemInitializingException;

public class ScreenshotsCreator extends ProblemRegister {
  private final static Logger LOGGER = LoggerFactory.getLogger(ScreenshotsCreator.class);

  private static final ScreenshotsCreator screenshotsCreator = new ScreenshotsCreator();

  public static void createScreenshot(Problem addedProblem) {
    screenshotsCreator.register(addedProblem);
  }

  public static void updateScreenshot(Problem problem) {
    screenshotsCreator.updateRegistration(problem);
  }

  @Override
  protected void save(Problem problem) {
    var snapshotsDir = String.join(File.separator, workDirectory, "__snapshots__");

    try {
      Files.createDirectories(solutionDirectoryPath);
      Files.createDirectories(Paths.get(snapshotsDir));

      saveSolutionToFiles(problem);
      addTestFile();
      TestingUtility.run(COMMAND, pathToFinalTestScript);

    } catch (IOException e) {
      var msg = String.format("Не удалось сохранить файлы для задачи № %s", problem.getId());
      LOGGER.error("{} Details: {}", msg, e.getMessage());
      throw new ProblemInitializingException(msg);
    }
  }

  @Override
  protected void update(Problem problem) {
    try {
      saveSolutionToFiles(problem);
      cleanSnapshots();
      TestingUtility.run(COMMAND, pathToFinalTestScript);
    } catch (IOException e) {
      var msg = String.format("Не удалось обновить файлы для задачи № %s", problem.getId());
      LOGGER.error("{} Details: {}", msg, e.getMessage());
      throw new ProblemInitializingException(msg);
    }
  }

  private void cleanSnapshots() {
    var snapshotsDir = String.join(File.separator, workDirectory, "__snapshots__");
    var path = Paths.get(snapshotsDir);
    Arrays.stream(path.toFile().listFiles()).forEach(File::delete);
  }

  private void saveSolutionToFiles(Problem problem) throws IOException {
    var solution = problem.getReferenceSolution();
    var htmlFile = Paths.get(solutionDirectory, HTML_FILE_NAME);
    writeContentToFile(solution.getHtmlPart(), htmlFile);
    var cssFile = Paths.get(solutionDirectory, CSS_FILE_NAME);
    writeContentToFile(solution.getCssPart(), cssFile);
  }

  private void addTestFile() throws IOException {
    var inputFilePath = Paths.get(PATH_TO_TEMPLATE);
    var template = Files.readString(inputFilePath);
    var contentOfScript = template.replaceFirst("path_to_snapshots_replace_mark", "'__snapshots__'");
    var outputFilePath = Paths.get(pathToFinalTestScript);
    writeContentToFile(contentOfScript, outputFilePath);
  }
}
