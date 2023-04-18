package ru.hh.school.checkupextension.core.data.daoimpl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ru.hh.school.checkupextension.core.data.dao.SubmissionDao;
import ru.hh.school.checkupextension.core.data.entity.Submission;

import java.util.List;

/**
 * Класс SubmissionDaoImpl, который реализует интерфейс SubmissionDao,
 * и используется в SubmissionService вместо SubmissionRepository.
 */
public class SubmissionDaoImpl implements SubmissionDao {
  private final EntityManager entityManager;

  public SubmissionDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Submission getById(Long id) {
    return entityManager.find(Submission.class, id);
  }

  @Override
  public List<Submission> getAll() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Submission> query = builder.createQuery(Submission.class);
    Root<Submission> root = query.from(Submission.class);
    query.select(root);
    TypedQuery<Submission> typedQuery = entityManager.createQuery(query);
    return typedQuery.getResultList();
  }

  @Override
  public Submission create(Submission submission) {
    entityManager.persist(submission);
    return submission;
  }

  @Override
  public Submission update(Submission submission) {
    return entityManager.merge(submission);
  }

  @Override
  public void delete(Long id) {
    Submission submission = getById(id);
    entityManager.remove(submission);
  }
}
