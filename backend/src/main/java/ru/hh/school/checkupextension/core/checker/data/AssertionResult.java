package ru.hh.school.checkupextension.core.checker.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AssertionResult(String fullName, String title, String status) {

  @JsonCreator
  public AssertionResult(
      @JsonProperty("fullName") String fullName,
      @JsonProperty("title") String title,
      @JsonProperty("status") String status) {
    this.fullName = fullName;
    this.title = title;
    this.status = status;
  }
}
