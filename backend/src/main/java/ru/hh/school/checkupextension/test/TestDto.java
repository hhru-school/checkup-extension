package ru.hh.school.checkupextension.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDto {
    @JsonProperty("test_data")
    private String testData;

    public TestDto(String testData) {
        this.testData = testData;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }
}
