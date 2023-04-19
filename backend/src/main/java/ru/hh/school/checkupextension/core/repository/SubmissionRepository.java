package ru.hh.school.checkupextension.core.repository;


import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.Submission;

import java.util.List;
import java.util.Optional;

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
}
