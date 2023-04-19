package ru.hh.school.checkupextension.core.repository;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.VerificationEntity;

import java.util.List;
import java.util.Optional;

public class VerificationRepository extends GenericRepository<VerificationEntity> {

    @Inject
    public VerificationRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<VerificationEntity> getById(Long id) {
        return getById(VerificationEntity.class, id);
    }

    @Override
    public List<VerificationEntity> getAll() {
        return null;
    }
}
