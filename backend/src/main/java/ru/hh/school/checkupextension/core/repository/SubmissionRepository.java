package ru.hh.school.checkupextension.core.repository;


import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;
import ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfoEntity;

import java.util.List;
import java.util.Optional;

public class SubmissionRepository extends GenericRepository<SubmissionEntity> {
    @Inject
    public SubmissionRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<SubmissionEntity> getById(Long id) {
        return Optional.ofNullable(getSession().find(SubmissionEntity.class, id));
    }

    @Override
    public List<SubmissionEntity> getAll() {
        return null;
    }

    public Optional<SubmissionEntity> getForUserById(long userId, long submissionId) {
        var query = getSession().createQuery(
            """
                   FROM SubmissionEntity s
                   WHERE s.id = :id AND
                         s.user = :user_id
               """, SubmissionEntity.class)
            .setParameter("id", submissionId)
            .setParameter("user_id", userId);

        return Optional.ofNullable(query.uniqueResult());
    }

    public List<SubmissionShortInfoEntity> getUserSubmissionsShortInfo(long userId, long problemId) {
        return getSession().createQuery(
                """
                       SELECT
                        new ru.hh.school.checkupextension.core.data.entity.SubmissionShortInfoEntity(s.id, s.status)
                       FROM SubmissionEntity s
                       WHERE s.user = :user_id AND
                             s.problem.id = :problem_id
                  """, SubmissionShortInfoEntity.class)
                .setParameter("user_id", userId)
                .setParameter("problem_id", problemId)
                .list();
    }

    public long getTotalSubmissionsOfTaskForUser(long userId, long taskId) {
        var query = getSession().createQuery(
         """
                SELECT count(*)
                FROM SubmissionEntity s
                WHERE s.user = :user_id AND
                      s.problem.id = :problem_id
            """)
         .setParameter("user_id", userId)
         .setParameter("problem_id", taskId);

        return (long) query.uniqueResult();
    }
}
