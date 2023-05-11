package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ContestProblemDto(Long id, String condition, byte type, byte maxAttempts) {
  @JsonCreator
  public ContestProblemDto(
      @JsonProperty("Id") Long id,
      @JsonProperty("Condition") String condition,
      @JsonProperty("Type") byte type,
      @JsonProperty("MaxAttempts") byte maxAttempts
  ) {
    this.id = id;
    this.condition = condition;
    this.type = type;
    this.maxAttempts = maxAttempts;
  }
}
