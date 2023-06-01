package ru.hh.school.checkupextension.core.checker.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Test(boolean success, List<TestResult> testResults) {
  public static final String FAILED = "failed";
  public static final String PASSED = "passed";

  @JsonCreator
  public Test(
      @JsonProperty("success") boolean success,
      @JsonProperty("testResults") List<TestResult> testResults
  ) {
    this.success = success;
    this.testResults = testResults;
  }
}
