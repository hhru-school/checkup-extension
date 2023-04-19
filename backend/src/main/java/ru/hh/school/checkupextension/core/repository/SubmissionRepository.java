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
}
