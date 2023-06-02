package ru.hh.school.checkupextension.core.data.dto.contest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.checkupextension.utils.constant.ProblemDtoJsonPropertyName;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContestProblemInfoDto {
  public final Long id;
  public final String title;
  public final String description;
  public final String type;

  @JsonCreator
  public ContestProblemInfoDto(
      @JsonProperty(ProblemDtoJsonPropertyName.ID) Long id,
      @JsonProperty(ProblemDtoJsonPropertyName.TITLE) String title,
      @JsonProperty(ProblemDtoJsonPropertyName.DESCRIPTION) String description,
      @JsonProperty(ProblemDtoJsonPropertyName.TYPE) String type
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
  }
}
