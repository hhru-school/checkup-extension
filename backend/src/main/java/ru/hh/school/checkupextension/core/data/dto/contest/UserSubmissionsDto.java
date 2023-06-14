package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record UserSubmissionsDto(List<ContestSubmissionShortInfoDto> submissions) {
  @JsonCreator
  public UserSubmissionsDto(@JsonProperty("submissions") List<ContestSubmissionShortInfoDto> submissions) {
    this.submissions = submissions;
  }
}
