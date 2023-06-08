package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ContestDto(List<ContestProblemInfoDto> problems) {
  @JsonCreator
  public ContestDto(@JsonProperty("problems") List<ContestProblemInfoDto> problems) {
    this.problems = problems;
  }
}
