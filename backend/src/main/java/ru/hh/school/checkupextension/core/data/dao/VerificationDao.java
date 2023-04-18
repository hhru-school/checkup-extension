package ru.hh.school.checkupextension.core.data.dao;

import ru.hh.school.checkupextension.core.data.entity.Verification;

import java.util.List;

/**
 * интерфейс VerificationDao с методами CRUD
 */
public interface VerificationDao {
  Verification getById(Long id);

  List<Verification> getAll();

  Verification create(Verification verification);

  Verification update(Verification verification);

  void delete(Long id);
}
