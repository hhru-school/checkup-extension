package ru.hh.school.checkupextension.core.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepository<TEntity> implements Repository<TEntity> {
  protected SessionFactory sessionFactory;

  public GenericRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public abstract List<TEntity> getAll();

  public abstract Optional<TEntity> getById(Long id);

  protected Optional<TEntity> getById(Class<TEntity> clazz, Long id) {
    return Optional.ofNullable(getSession().get(clazz, id));
  }

  public TEntity create(TEntity entity) {
    getSession().persist(entity);
    return entity;
  }

  public TEntity update(TEntity entity) {
    getSession().merge(entity);
    return entity;
  }

  public void delete(Long id) {
    Optional<TEntity> entity = getById(id);
      if (entity.isEmpty()) {
          return;
      }
    getSession().remove(entity);
  }

  protected Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
