package ru.hh.school.checkupextension.core.repository;

import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.Problem;
import ru.hh.school.checkupextension.core.data.utils.PaginationResult;

public class ProblemRepository extends GenericRepository<Problem> {
  @Inject
  public ProblemRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public Optional<Problem> getById(Long id) {
    return getById(Problem.class, id);
  }

  @Override
  public List<Problem> getAll() {
    return getSession().createQuery("SELECT p FROM Problem p", Problem.class).getResultList();
  }

  public PaginationResult<Problem> getProblems(int firstRecord, int size) {
    var totalRecords = getSession()
        .createQuery("SELECT count(*) FROM Problem", Long.class)
        .getSingleResult();
    var totalPages = Math.ceil(totalRecords / (double) size);
    var records = getProblemsFrom(firstRecord, size);
    return new PaginationResult<>(firstRecord, size, (int) totalPages, totalRecords, records);
  }

  public List<Problem> getProblemsFrom(int firstRecordNumber, int size) {
    return getSession().createQuery("FROM Problem", Problem.class)
        .setFirstResult((firstRecordNumber - 1) * size)
        .setMaxResults(size)
        .getResultList();
  }

  public List<Problem> getActiveProblems() {
    return getSession().createQuery("SELECT p FROM Problem p WHERE p.active = true", Problem.class).getResultList();
  }
}
