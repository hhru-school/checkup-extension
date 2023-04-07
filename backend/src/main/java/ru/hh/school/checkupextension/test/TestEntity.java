package ru.hh.school.checkupextension.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_table")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String testData;

    public TestEntity() { }

    public TestEntity(String testData) {
        this.testData = testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getTestData() {
        return testData;
    }
}
