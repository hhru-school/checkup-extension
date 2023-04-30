package ru.hh.school.checkupextension.core.repository;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.ProblemEntity;

import java.util.List;
import java.util.Optional;

/**
 * Класс, отвечающий за работу с базой данных и взаимодействие с таблицей, хранящей информацию о задачах.
 */
public class ProblemRepository extends GenericRepository<ProblemEntity> {
    @Inject
    public ProblemRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<ProblemEntity> getById(Long id) {
        return Optional.ofNullable(getSession().get(ProblemEntity.class, id));
    }

    @Override
    public List<ProblemEntity> getAll() {
        return getSession().createQuery("SELECT p FROM ProblemEntity p", ProblemEntity.class).getResultList();
    }

    @Override
    public ProblemEntity create(ProblemEntity problem) {
        getSession().save(problem);
        return problem;
    }
}
