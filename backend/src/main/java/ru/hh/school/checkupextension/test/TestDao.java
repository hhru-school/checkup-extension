package ru.hh.school.checkupextension.test;

import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.checkupextension.utils.data.TransactionHelper;

public class TestDao {
    private final SessionFactory sessionFactory;

    private final TransactionHelper transactionHelper;

    @Inject
    public TestDao(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public void writeTestData(TestEntity entity) {
        transactionHelper.inTransaction(() ->
            getSession().persist(entity));
    }

    public TestEntity readTestData() {
        return transactionHelper.inTransaction(() ->
                getSession()
                .createNativeQuery("SELECT * FROM test_table LIMIT 1", TestEntity.class)
                .getSingleResult());
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
