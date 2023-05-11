package ru.hh.school.checkupextension.core.repository;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.core.data.entity.Verification;

import java.util.List;
import java.util.Optional;

public class VerificationRepository extends GenericRepository<Verification> {
  @Inject
  public VerificationRepository(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public Optional<Verification> getById(Long id) {
    return getById(Verification.class, id);
  }

  @Override
  public List<Verification> getAll() {
    return null;
  }
}
