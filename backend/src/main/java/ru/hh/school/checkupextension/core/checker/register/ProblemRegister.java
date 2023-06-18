package ru.hh.school.checkupextension.core.checker.register;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static ru.hh.school.checkupextension.core.checker.environment.TestEnvironment.PATH_TO_PROBLEMS_DIRECTORY;
import static ru.hh.school.checkupextension.core.checker.environment.TestEnvironment.TEST_SCRIPT_NAME;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public abstract class ProblemRegister {
  protected String workDirectory;
  protected Path workDirectoryPath;
  protected String solutionDirectory;
  protected Path solutionDirectoryPath;
  protected String pathToFinalTestScript;


  public void register(Problem problem) {
    initPaths(problem);
    save(problem);
  }

  public void updateRegistration(Problem problem) {
    initPaths(problem);
    update(problem);
  }

  protected void initPaths(Problem problem) {
    var problemId = String.valueOf(problem.getId());
    workDirectory = String.join(File.separator, PATH_TO_PROBLEMS_DIRECTORY, problemId);
    workDirectoryPath = Paths.get(workDirectory);
    solutionDirectory = String.join(File.separator, workDirectory, "solution");
    solutionDirectoryPath = Paths.get(solutionDirectory);
    pathToFinalTestScript = String.join(File.separator, workDirectory, TEST_SCRIPT_NAME);
  }

  protected abstract void save(Problem problem);

  protected abstract void update(Problem problem);

  protected void writeContentToFile(String content, Path file) throws IOException {
    if (Files.exists(file)) {
      Files.delete(file);
    }
    Files.writeString(file, content);
  }
}
