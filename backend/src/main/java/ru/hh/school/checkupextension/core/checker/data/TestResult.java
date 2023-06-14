package ru.hh.school.checkupextension.core.checker.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TestResult(List<AssertionResult> results) {

  @JsonCreator
  public TestResult(
      @JsonProperty("assertionResults") List<AssertionResult> results
  ) {
    this.results = results;
  }
}
