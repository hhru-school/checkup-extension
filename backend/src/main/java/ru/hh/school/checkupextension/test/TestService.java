package ru.hh.school.checkupextension.test;

import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    private final TestDao testDao;

    @Inject
    public TestService(TestDao testDao) {
        this.testDao = testDao;
    }

    @Transactional
    public void saveTestData(TestEntity entity) {
        testDao.writeTestData(entity);
    }

    @Transactional(readOnly = true)
    public TestEntity getTestData() {
        return testDao.readTestData();
    }
}
