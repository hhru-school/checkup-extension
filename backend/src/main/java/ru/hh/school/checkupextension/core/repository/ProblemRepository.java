package ru.hh.school.checkupextension.core.repository;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;

import java.util.List;
import java.util.Optional;

public class ProblemRepository extends GenericRepository<ProblemEntity> {
    @Inject
    public ProblemRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<ProblemEntity> getById(Long id) {
        return getById(ProblemEntity.class, id);
    }

    @Override
    public List<ProblemEntity> getAll() {
        return getSession().createQuery("SELECT p FROM Problem p", ProblemEntity.class).getResultList();
    }
}
