package ru.hh.school.checkupextension.core.data.daoimpl;

import jakarta.persistence.EntityManager;
import ru.hh.school.checkupextension.core.data.dao.ProblemDao;
import ru.hh.school.checkupextension.core.data.entity.Problem;

import java.util.List;

/**
 * Класс ProblemDaoImpl, который реализует интерфейс ProblemDao,
 * и используется в ProblemService вместо ProblemRepository.
 */
public class ProblemDaoImpl implements ProblemDao {
  private final EntityManager entityManager;

  public ProblemDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Problem getById(Long id) {
    return entityManager.find(Problem.class, id);
  }

  @Override
  public List<Problem> getAll() {
    return entityManager.createQuery("SELECT p FROM Problem p", Problem.class).getResultList();
  }

  @Override
  public Problem create(Problem problem) {
    entityManager.persist(problem);
    return problem;
  }

  @Override
  public Problem update(Problem problem) {
    entityManager.merge(problem);
    return problem;
  }

  @Override
  public void delete(Long id) {
    Problem problem = getById(id);
    entityManager.remove(problem);
  }
}
