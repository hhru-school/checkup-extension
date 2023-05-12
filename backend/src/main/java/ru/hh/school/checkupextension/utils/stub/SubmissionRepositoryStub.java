package ru.hh.school.checkupextension.utils.stub;

import ru.hh.school.checkupextension.core.data.entity.Submission;
import ru.hh.school.checkupextension.core.repository.Repository;
import ru.hh.school.checkupextension.utils.exception.core.SubmissionNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SubmissionRepositoryStub implements Repository<Submission> {
  private final Map<Long, Submission> entities = new HashMap<>();
  private Long currentId;

  public SubmissionRepositoryStub() {
    this.currentId = 1L;
  }

  @Override
  public Optional<Submission> getById(Long id) {
    return Optional.ofNullable(this.entities.get(id));
  }

  @Override
  public Submission create(Submission entity) {
    long id = this.currentId++;
    entity.setId(id);
    this.entities.put(id, entity);
    return entity;
  }

  @Override
  public Submission update(Submission entity) {
    long id = entity.getId();
      if (!this.entities.containsKey(id)) {
          throw new SubmissionNotFoundException(id);
      }
    this.entities.replace(id, entity);
    return entity;
  }

  @Override
  public void delete(Long id) {
      if (!this.entities.containsKey(id)) {
          throw new SubmissionNotFoundException(id);
      }
    this.entities.remove(id);
  }

  @Override
  public List<Submission> getAll() {
    return this.entities.values().stream().toList();
  }
}
