package ru.hh.school.checkupextension.core.data.dao;

import ru.hh.school.checkupextension.core.data.entity.Problem;

import java.util.List;

/**
 * интерфейс ProblemDao с методами CRUD
 */
public interface ProblemDao {
  Problem getById(Long id);

  List<Problem> getAll();

  Problem create(Problem problem);

  Problem update(Problem problem);

  void delete(Long id);
}
