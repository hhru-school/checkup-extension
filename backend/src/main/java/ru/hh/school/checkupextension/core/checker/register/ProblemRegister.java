package ru.hh.school.checkupextension.core.checker.register;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static ru.hh.school.checkupextension.core.checker.environment.TestEnvironment.PATH_TO_PROBLEMS_DIRECTORY;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public abstract class ProblemRegister {
  public void register(Problem problem) {
    var problemId = String.valueOf(problem.getId());
    var workDir = String.join(File.separator, PATH_TO_PROBLEMS_DIRECTORY, problemId);
    var solutionDir = String.join(File.separator, workDir, "solution");

    save(problem, workDir, solutionDir);
  }

  public void update(Problem problem) {
    var problemId = String.valueOf(problem.getId());
    var workDir = String.join(File.separator, PATH_TO_PROBLEMS_DIRECTORY, problemId);
    var solutionDir = String.join(File.separator, workDir, "solution");
    update(problem, workDir, solutionDir);
  }

  protected abstract void save(Problem problem, String workDir, String solutionDir);
  protected abstract void update(Problem problem, String workDir, String solutionDir);

  protected void writeContentToFile(String content, Path file) throws IOException {
    if (Files.exists(file)) {
      Files.delete(file);
    }
    Files.writeString(file, content);
  }
}
