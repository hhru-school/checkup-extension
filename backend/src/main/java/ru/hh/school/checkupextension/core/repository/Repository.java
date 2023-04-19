package ru.hh.school.checkupextension.core.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<TEntity> {
    Optional<TEntity> getById(Long id);
    TEntity create(TEntity entity);
    TEntity update(TEntity entity);
    void delete(Long id);
    List<TEntity> getAll();
}
