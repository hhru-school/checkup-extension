package ru.hh.school.checkupextension.core.repository;


import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.SubmissionEntity;

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
        var query= getSession().createQuery(
                        """ 
                              FROM SubmissionEntity s
                              WHERE s.id = :id AND
                                    s.user = :user_id
                           """, SubmissionEntity.class);
        query.setParameter("id", submissionId);
        query.setParameter("user_id", userId);

        return Optional.ofNullable(query.uniqueResult());
    }

    public long getTotalSubmissionsOfTaskForUser(long userId, long taskId) {
        var query = getSession().createQuery("""
                                    SELECT count(*)
                                    FROM SubmissionEntity s
                                    WHERE s.user = :user_id AND
                                          s.problem.id = :problem_id
                                    """);
        query.setParameter("user_id", userId);
        query.setParameter("problem_id", taskId);
        return (long) query.uniqueResult();
    }
}
