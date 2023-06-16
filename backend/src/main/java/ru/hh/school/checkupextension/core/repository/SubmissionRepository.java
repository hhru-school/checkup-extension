package ru.hh.school.checkupextension.core.repository;


import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfo;

public class SubmissionRepository extends GenericRepository<Submission> {
  @Inject
  public SubmissionRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public Optional<Submission> getById(Long id) {
    return Optional.ofNullable(getSession().find(Submission.class, id));
  }

  @Override
  public List<Submission> getAll() {
    return null;
  }

  public Optional<Submission> getForUserById(long userId, long submissionId) {
    var query = getSession().createQuery("""
            FROM Submission s
            WHERE s.id = :id AND
                  s.user = :user_id
        """, Submission.class).setParameter("id", submissionId).setParameter("user_id", userId);

    return Optional.ofNullable(query.uniqueResult());
  }

  public List<SubmissionShortInfo> getUserSubmissionsShortInfo(long userId, long problemId) {
    return getSession().createQuery("""
             SELECT
              new ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfo(s.id, s.creationDateTime, s.status)
             FROM Submission s
             WHERE s.user = :user_id AND
                   s.problem.id = :problem_id
        """, SubmissionShortInfo.class).setParameter("user_id", userId).setParameter("problem_id", problemId).list();
  }

  public long getTotalSubmissionsOfTaskForUser(long userId, long taskId) {
    var query = getSession().createQuery("""
            SELECT count(*)
            FROM Submission s
            WHERE s.user = :user_id AND
                  s.problem.id = :problem_id
        """).setParameter("user_id", userId).setParameter("problem_id", taskId);

    return (long) query.uniqueResult();
  }

  public void updateSubmissionStatus(long id, byte status) {
    getSession().createQuery("""
        UPDATE Submission s
        SET s.status := :status
        WHERE s.id := :id
        """)
        .setParameter("id", id)
        .setParameter("status", status)
        .executeUpdate();
  }
}
