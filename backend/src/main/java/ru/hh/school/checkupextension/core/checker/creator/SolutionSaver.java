package ru.hh.school.checkupextension.core.checker.creator;

import java.io.File;
import ru.hh.school.checkupextension.core.checker.environment.TestEnvironment;
import ru.hh.school.checkupextension.core.data.entity.Problem;

public abstract class SolutionSaver {
  public void saveSolution(Problem problem) {
    var problemId = String.valueOf(problem.getId());
    var workDir = String.join(File.separator, TestEnvironment.PATH_TO_PROBLEMS_DIRECTORY, problemId);
    var solutionDir = String.join(File.separator, workDir, "solution");

    save(problem, workDir, solutionDir);
  }

  protected abstract void save(Problem problem, String workDir, String solutionDir);
}
