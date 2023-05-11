package ru.hh.school.checkupextension.utils.stub;

import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.repository.Repository;

import java.util.List;
import java.util.Optional;

public class ProblemRepositoryStub implements Repository<Problem> {
  @Override
  public Optional<Problem> getById(Long id) {
    var problem = new Problem();
    problem.setId(1L);
    problem.setCondition("""
        {
            Statement: "markdown",
            Screenshot: "url"
        }
        """);
    problem.setType((byte) 1);
    problem.setMaxAttempts((short) 15);
    return Optional.of(problem);
  }

  @Override
  public Problem create(Problem problem) {
    return null;
  }

  @Override
  public Problem update(Problem problem) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public List<Problem> getAll() {
    return null;
  }
}
