package ru.hh.school.checkupextension.core.data.dao;

import ru.hh.school.checkupextension.core.data.entity.Submission;

import java.util.List;

/**
 * интерфейс SubmissionDao с методами CRUD
 */
public interface SubmissionDao {
  Submission getById(Long id);

  List<Submission> getAll();

  Submission create(Submission submission);

  Submission update(Submission submission);

  void delete(Long id);
}
