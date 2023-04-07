package ru.hh.school.checkupextension.test;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
    private final SessionFactory sessionFactory;

    @Inject
    public TestDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void writeTestData(TestEntity entity) {
        session().persist(entity);
    }

    public TestEntity readTestData() {
        return session()
          .createNativeQuery("SELECT * FROM test_table LIMIT 1", TestEntity.class)
          .getSingleResult();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
